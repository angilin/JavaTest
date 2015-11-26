package base.intercepter;

import java.lang.reflect.Proxy;

public class MyProxyFactory{
	/**
	 * 实例Service对象
	 * 
	 * @param serviceName
	 *            String
	 * @return Object
	 */
	public static Object getProxy(Object object) {
		// 代理的处理类
		ProxyHandler handler = new ProxyHandler();
		// 把该Person实例托付给代理操作
		handler.setTarget(object);
		// 第一个参数是用来创建动态代理的ClassLoader对象，只要该对象能访问Person接口即可
		// 第二个参数是接口数组，正是代理该接口数组
		// 第三个参数是代理包含的处理实例
		return Proxy.newProxyInstance(PersonImpl.class.getClassLoader(), object
				.getClass().getInterfaces(), handler);
	}
}