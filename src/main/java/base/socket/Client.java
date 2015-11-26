package base.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * http://haohaoxuexi.iteye.com/blog/1979837
 * socket编程例子
 */
public class Client {

	public static void main(String args[]) throws Exception {
		// 为了简单起见，所有的异常都直接往外抛
		String host = "127.0.0.1"; // 要连接的服务端IP地址
		int port = 8899; // 要连接的服务端对应的监听端口
		// 与服务端建立连接
		Socket client = new Socket(host, port);
		// 建立连接后就可以往服务端写数据了
		Writer writer = new OutputStreamWriter(client.getOutputStream(),"UTF-8");
		//因为用bufferReader按行读取，所以要加\n
		writer.write("客户端发送。eof\n");
		// 对于客户端往Socket的输出流里面写数据传递给服务端要注意一点，如果写操作之后程序不是对应着输出流的关闭
		// 而是进行其他阻塞式的操作（比如从输入流里面读数据），记住要flush一下，只有这样服务端才能收到客户端发送的数据
		// 否则可能会引起两边无限的互相等待。
		writer.flush();

		// 写完以后进行读操作
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
		
		//假设有这样一种需求，我们的客户端需要通过Socket从服务端获取到XX信息，然后给用户展示在页面上。我们知道Socket在读数据的时候是阻塞式的，如果没有读到数据程序会一直阻塞在那里。在同步请求的时候我们肯定是不能允许这样的情况发生的，这就需要我们在请求达到一定的时间后控制阻塞的中断，让程序得以继续运行。Socket为我们提供了一个setSoTimeout()方法来设置接收数据的超时时间，单位是毫秒。当设置的超时时间大于0，并且超过了这一时间Socket还没有接收到返回的数据的话，Socket就会抛出一个SocketTimeoutException。
		//假设我们需要控制我们的客户端在开始读取数据10秒后还没有读到数据就中断阻塞的话我们可以这样做
		//设置超时间为10秒  
		//client.setSoTimeout(10*1000);
		//之后在br.readLine那里捕获SocketTimeoutException
		
		StringBuilder sb = new StringBuilder();
		int index;
		String temp;
		while ((temp = br.readLine()) != null) {
			//如果不加判断，则除非服务器端主动关闭阻塞，否则客户端无法进行后续的writer操作
			if ((index = temp.indexOf("eof")) != -1) {// 遇到eof时就结束接收
				sb.append(temp.substring(0, index));
				break;
			}
			sb.append(temp);
		}
		System.out.println("from server: " + sb);
		writer.close();
		br.close();
		client.close();
	}

}