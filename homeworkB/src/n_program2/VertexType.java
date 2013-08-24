package n_program2;

//用了一个beginPos解决了我们之前说的，第二个词出现两次的情况，以及第一个字和后面的某个字不相邻也能组成一个词的情况
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
		// TODO 自动生成的方法存根
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