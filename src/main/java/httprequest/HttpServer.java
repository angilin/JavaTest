package httprequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 参考资料：http://blog.csdn.net/a9529lty/article/details/7174259
 * socket实现的httpServer编程例子
 */
public class HttpServer {

	public static void main(String args[]) throws IOException {
		// 为了简单起见，所有的异常信息都往外抛
		int port = 8089;
		// 定义一个ServerSocket监听在端口8089上
		ServerSocket server = new ServerSocket(port);
		while (true) {
			// server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
			// 如果从输入流中没有读取到数据程序会一直在那里不动，直到客户端往Socket的输出流中写入了数据，或关闭了Socket的输出流。
			Socket socket = server.accept();
			// 每接收到一个Socket就建立一个新的线程来处理它
			new Thread(new Task(socket)).start();
		}

	}

	/**
	 * 用来处理Socket请求的
	 */
	static class Task implements Runnable {

		private Socket socket;
		
		private String encoding = "UTF-8";

		public Task(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				handleSocket();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 跟客户端Socket进行通信
		 * 
		 * @throws Exception
		 */
		private void handleSocket() throws Exception {

			System.out.println("连接到服务器的用户:" + socket);
			try {
				//第一阶段: 打开输入流
				InputStream is = socket.getInputStream();

				System.out.println("客户端发送的请求信息: >>>>>>>>>>>>>>>>>>>>>>>>>");
				//读取第一行, 请求地址
				String line = readLine(is, 0);
				//打印请求行
				System.out.print(line);
				//<Method> <URL> <HTTP/Version> <\r\n>  取的是URL部分
				//Method为post时，<URL>为/
				//Method为get时，<URL>为以/?开始的参数=值
				//获得请求的资源的地址<URL>部分
				String resource = line.substring(line.indexOf('/'), line
						.lastIndexOf(" HTTP/"));
				
				//反编码 URL地址
				resource = URLDecoder.decode(resource, encoding);
				//获取请求方法, GET 或者 POST
				String method = new StringTokenizer(line).nextElement()
						.toString();
				//如果为POST方法，则会有消息体长度
				int contentLength = 0;

				// 读取所有浏览器发送过来的请求参数头部信息
				do {
					line = readLine(is, 0);
					//如果有Content-Length消息头时取出
					if (line.startsWith("Content-Length")) {
						contentLength = Integer.parseInt(line.split(":")[1]
								.trim());
					}
					//打印请求部信息
					System.out.print(line);
					//如果遇到了一个单独的回车换行，则表示请求头结束
				} while (!line.equals("\r\n"));
				//如果是POST请求，则有请求体
				if ("POST".equalsIgnoreCase(method)) {
					//注，这里只是简单的处理表单提交的参数，而对于上传文件这里是不能这样处理的，
					//因为上传的文件时消息体不只是一行，会有多行消息体
					System.out.print(readLine(is, contentLength));
					System.out.println();
				}

				System.out.println("客户端发送的请求信息结束 <<<<<<<<<<<<<<<<<<<<<<<<<<");
				System.out.println("用户请求的资源是:" + resource);
				System.out.println("请求的类型是: " + method);
				System.out.println();

				//用 writer对客户端 socket输出一段 HTML代码
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);
				//返回应答消息,并结束应答
				//200为HTTP状态码
				//详细见  http://baike.baidu.com/link?url=hDJi9nOMknGvXsUvRc7nHmIWDDPkEiuN8r5_n_pRo634VQdWgjTSUJ5ZtwkZPc1vYk4qqkbR6CDk1YMOqSuXN_
				out.println("HTTP/1.0 200 OK");
				out.println("Content-Type:text/html;charset=" + encoding);
				//根据 HTTP协议,空行将结束头信息
				out.println();

				out.println("<h1> Hello Http Server</h1>");
				out.println("你好, 这是一个 Java HTTP 服务器 demo 应用.<br>");
				out.println("您请求的路径是: " + resource + "<br>");
				out.close();

				socket.close();
			} catch (Exception e) {
				System.out.println("HTTP服务器错误:" + e.getLocalizedMessage());
			}
			finally{
				System.out.println(socket + "离开了HTTP服务器");
			}
		
		}
		
		/*
		 * 这里我们自己模拟读取一行，因为如果使用API中的BufferedReader时，它是读取到一个回车换行后
		 * 才返回，否则如果没有读取，则一直阻塞，这就导致如果为POST请求时，表单中的元素会以消息体传送，
		 * 这时，消息体最末按标准是没有回车换行的，如果此时还使用BufferedReader来读时，则POST提交
		 * 时会阻塞。如果是POST提交时我们按照消息体的长度Content-Length来截取消息体，这样就不会阻塞
		 */
		private String readLine(InputStream is, int contentLe) throws IOException {
			ArrayList lineByteList = new ArrayList();
			byte readByte;
			int total = 0;
			if (contentLe != 0) {
				do {
					readByte = (byte) is.read();
					lineByteList.add(Byte.valueOf(readByte));
					total++;
				} while (total < contentLe);//消息体读还未读完
			} else {
				do {
					readByte = (byte) is.read();
					lineByteList.add(Byte.valueOf(readByte));
				} while (readByte != 10);
			}

			byte[] tmpByteArr = new byte[lineByteList.size()];
			for (int i = 0; i < lineByteList.size(); i++) {
				tmpByteArr[i] = ((Byte) lineByteList.get(i)).byteValue();
			}
			lineByteList.clear();

			String tmpStr = new String(tmpByteArr, encoding);
			/* http请求的header中有一个Referer属性，这个属性的意思就是如果当前请求是从别的页面链接过
			 * 来的，那个属性就是那个页面的url，如果请求的url是直接从浏览器地址栏输入的就没有这个值。得
			 * 到这个值可以实现很多有用的功能，例如防盗链，记录访问来源以及记住刚才访问的链接等。另外，浏
			 * 览器发送这个Referer链接时好像固定用UTF-8编码的，所以在GBK下出现乱码，我们在这里纠正一下
			 */
			if (tmpStr.startsWith("Referer")) {//如果有Referer头时，使用UTF-8编码
				tmpStr = new String(tmpByteArr, "UTF-8");
			}
			return tmpStr;
		}
	}

}
