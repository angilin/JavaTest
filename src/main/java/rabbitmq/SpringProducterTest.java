package rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringProducterTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"conf/applicationContext-rabbitmq-producter.xml");
		AmqpTemplate amqpTemplate = (AmqpTemplate) context
				.getBean("amqpTemplate");
	
		// 模拟发送消息
		for (int i = 0; i < 10000; i++) {
			String message = "this is test message!Come from Producter:";
			message += i;
			amqpTemplate.convertAndSend("r_msg_test", message);
			System.out.println("Sent:" + message);
		}
	}
}
