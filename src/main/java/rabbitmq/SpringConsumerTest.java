package rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringConsumerTest implements MessageListener {
	//使用监听器接收消息
	public void onMessage(Message arg0) {
		String message = new String(arg0.getBody());
		try {
			Thread.sleep(1000);
			System.out.println("Recv:" + message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//启动容器
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"conf/applicationContext-rabbitmq-consumer.xml");
	}
}
