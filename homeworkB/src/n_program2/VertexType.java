package n_program2;

//����һ��beginPos���������֮ǰ˵�ģ��ڶ����ʳ������ε�������Լ���һ���ֺͺ����ĳ���ֲ�����Ҳ�����һ���ʵ����
public class VertexType
{
	private String token;
	private int beginPos;
	
	public VertexType()
	{
		token=null;
		beginPos=0;
	}	
	public VertexType(String token,int beginPos)
	{
		this.token=token;
		this.beginPos=beginPos;
	}
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}

	@Override
	public String toString()
	{
		// TODO �Զ����ɵķ������
		return token;
	}
	public int getBeginPos() 
	{
		return beginPos;
	}
	public void setBeginPos(int beginPos) 
	{
		this.beginPos = beginPos;
	}
}