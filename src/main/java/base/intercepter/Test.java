package base.intercepter;

//http://hope598.iteye.com/blog/739641
//拦截器实现方式
public class Test {
	public static void main(String[] args) {
		// 创建一个Person实例，该实例将被作为代理的目标对象
		Person targetObject = new PersonImpl();
		Person person = null;
		// 以目标对象创建代理
		Object proxy = MyProxyFactory.getProxy(targetObject);
		if (proxy instanceof Person) {
			person = (Person) proxy;
		}
		// 测试代理的方法
		person.getName();
		person.getAge();
	}
}
