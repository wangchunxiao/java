package com.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteWatched implements Watched
{
	private List<Watcher> list=new ArrayList<Watcher>();
	//注意这里可以直接初始化，调用Java的方法。一定要初始化，不初始化就和C的指针没有初始化就调用是一样的
	
	@Override
	public void addWatcher(Watcher w)
	{
		// TODO 自动生成的方法存根
		list.add(w);
	}

	@Override
	public void removeWatcher(Watcher w)
	{
		// TODO 自动生成的方法存根
		list.remove(w);
	}

	@Override
	public void notifyWatcher(String str)
	{
		// TODO 自动生成的方法存根
		for(Watcher w:list)
		{
			w.update(str);
		}
	}

}
