package base.intercepter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
	// 需要被代理的目标对象
	private Object target;
	// 创建拦截器实例
	PersonIntercepter pi = new PersonIntercepter();

	// 执行代理的目标方法时，该invoke方法会被自动调用
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Exception {
		Object result = null;
		// 如果被调用方法的方法名为getName
		if (method.getName().equals("getName")) {
			// 调用拦截器方法1
			pi.method1();
			result = method.invoke(target, args);
			// 调用拦截器方法2
			pi.method2();
		} else {
			result = method.invoke(target, args);
		}
		return result;
	}

	// 用户设置传入目标对象的方法
	public void setTarget(Object o) {
		this.target = o;
	}

}
