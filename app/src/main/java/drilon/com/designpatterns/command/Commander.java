package drilon.com.designpatterns.command;

import java.util.ArrayList;

public class Commander {

	private ArrayList<ObjectWrapper> objectWrappers;
	private static Commander commander;

	private Commander() {
		objectWrappers = new ArrayList<>();
	}

	public static Commander getInstance() {
		if (commander == null) {
			commander = new Commander();
		}
		return commander;
	}

	public void subscribe(Object subscriber, Class postClass) {
		try {
			objectWrappers.add(new ObjectWrapper(subscriber, postClass));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public void unSubscribe(Object unSubscriber, Class postClass) {
		for (int i = 0; i < objectWrappers.size(); i++) {
			ObjectWrapper objectWrapper = objectWrappers.get(i);
			if (objectWrapper.getSubscribedObject().equals(unSubscriber)
				&& objectWrapper.getPostClass().equals(postClass)) {
				objectWrappers.remove(i);
				return;
			}
		}
	}

	public void post(Object postedObject) {
		for (int i = 0; i < objectWrappers.size(); i++) {
			ObjectWrapper objectWrapper = objectWrappers.get(i);
			if (objectWrapper.getPostClass().equals(postedObject.getClass())) {
				try {
					objectWrapper.getMethod()
						.invoke(objectWrapper.getSubscribedObject(), postedObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
