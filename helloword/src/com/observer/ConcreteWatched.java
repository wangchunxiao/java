package com.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteWatched implements Watched
{
	private List<Watcher> list=new ArrayList<Watcher>();
	//ע���������ֱ�ӳ�ʼ��������Java�ķ�����һ��Ҫ��ʼ��������ʼ���ͺ�C��ָ��û�г�ʼ���͵�����һ����
	
	@Override
	public void addWatcher(Watcher w)
	{
		// TODO �Զ����ɵķ������
		list.add(w);
	}

	@Override
	public void removeWatcher(Watcher w)
	{
		// TODO �Զ����ɵķ������
		list.remove(w);
	}

	@Override
	public void notifyWatcher(String str)
	{
		// TODO �Զ����ɵķ������
		for(Watcher w:list)
		{
			w.update(str);
		}
	}

}
