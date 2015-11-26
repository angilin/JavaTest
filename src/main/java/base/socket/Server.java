package base.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * http://haohaoxuexi.iteye.com/blog/1979837
 * socket编程例子
 */
public class Server {

	public static void main(String args[]) throws IOException {
		// 为了简单起见，所有的异常信息都往外抛
		int port = 8899;
		// 定义一个ServerSocket监听在端口8899上
		ServerSocket server = new ServerSocket(port);
		/*System.out.println(server);
		System.out.println(server.getLocalPort());
		Socket socket = server.accept();
		//只有在客户端请求后才会打印
		System.out.println(socket!=null);
		System.out.println(socket);
		new Thread(new Task(socket)).start();*/
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
			// 跟客户端建立好连接之后，我们就可以获取socket的BufferedReader，并从中读取客户端发过来的信息了。
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			int index;
			String temp;
			while ((temp = br.readLine()) != null) {
				// 如果不加判断，则除非客户端主动关闭阻塞，否则服务器端无法进行后续的writer操作
				if ((index = temp.indexOf("eof")) != -1) {// 遇到eof时就结束接收
					sb.append(temp.substring(0, index));
					break;
				}
				sb.append(temp);
			}
			System.out.println("from client: " + sb);
			// 读完后写一句
			Writer writer = new OutputStreamWriter(socket.getOutputStream(),
					"UTF-8");
			writer.write("服务器端发送asdsadsadassdsadsadsa。eof\n");
			writer.flush();
			writer.close();
			br.close();
			socket.close();
		}
	}

}
