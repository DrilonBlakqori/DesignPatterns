package drilon.com.designpatterns.command;

import java.lang.reflect.Method;

public class ObjectWrapper {

	Method method;
	Class postClass;
	Object subscribedObject;

	public ObjectWrapper(Object subscribedObject, Class postClass) throws
		NoSuchMethodException {

		this.method = subscribedObject.getClass()
			.getMethod("onEvent", postClass);
		this.postClass = postClass;
		this.subscribedObject = subscribedObject;
	}

	public Method getMethod() {
		return method;
	}

	public Class getPostClass() {
		return postClass;
	}

	public Object getSubscribedObject() {
		return subscribedObject;
	}
}
