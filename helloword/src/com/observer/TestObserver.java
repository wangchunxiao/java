package com.observer;

public class TestObserver
{
	public static void main(String[] args)
	{
		Watched girl=new ConcreteWatched();
		Watcher watcher1=new ConcreteWatcher();
		Watcher watcher2=new ConcreteWatcher();
		Watcher watcher3=new ConcreteWatcher();
		
		girl.addWatcher(watcher1);
		girl.addWatcher(watcher2);
		girl.addWatcher(watcher3);
		
		girl.notifyWatcher("这个是主题(被观察的)调用通知方法,实际和Button一样是实例主题的内部调用");
	}
}
