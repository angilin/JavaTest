package rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringConsumerTest2 {
	//另一种接收消息的方式
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"conf/applicationContext-rabbitmq-producter.xml");
		AmqpTemplate amqpTemplate = (AmqpTemplate) context
				.getBean("amqpTemplate");
	
		while(true){
			String message = (String)amqpTemplate.receiveAndConvert("q_msg_test");
			System.out.println("Recv:" + message);
		}
	}
}
