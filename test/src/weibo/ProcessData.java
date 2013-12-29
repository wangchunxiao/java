package weibo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

public class ProcessData
{
	private File file;
	private ArrayList<WeiboInfo> weiboList;
	private ArrayList<WeiboInfoNew> weiboListNew;

	public ProcessData()
	{
		file = null;
		weiboList = null;
	}

	public ProcessData(File file)
	{
		this.file = file;
		this.weiboList = new ArrayList();
		this.weiboListNew=new ArrayList();
	}

	// 处理函数，处理数据
	public void prcess() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(file));

		String input;
		while (null != (input = br.readLine()))
		{
			WeiboInfo weibo = new WeiboInfo();
			WeiboInfoNew weiboNew=new WeiboInfoNew();

			String[] array = input.split("\t");
			for (int i = 0; i < array.length; i++)
			{
				switch (i)
				{
				case 0:
					weibo.setUrl(array[i]);
					weiboNew.setUrl(array[i]);
					break;
				case 1:
					weibo.setArticle(array[i]);
					weiboNew.setArticle(array[i]);
					break;
				case 2:
					weibo.setPublishDate(new MyDate(array[i]));
					weiboNew.setPublishDate(new MyDate(array[i]));
					break;
				case 3:
					weibo.setDiscuss(Integer.parseInt(array[i]));
					weiboNew.setDiscuss(Integer.parseInt(array[i]));
					break;
				case 4:
					weibo.setInsertDate(new MyDate(array[i]));
					weiboNew.setInsertDate(new MyDate(array[i]));
					break;
				case 5:
					weibo.setAuthorName(array[i]);
					weiboNew.setAuthorName(array[i]);
					break;
				case 6:
					weibo.setTransmit(Integer.parseInt(array[i]));
					weiboNew.setTransmit(Integer.parseInt(array[i]));
					break;
				default:
					break;
				}//switch
			}//for
			weibo.setTimeSubstraction((double)(weibo.getInsertDate().getTime()- weibo.getPublishDate().getTime())/(1000*60*60));
			weiboNew.setTimeSubstraction((double)(weibo.getInsertDate().getTime()- weibo.getPublishDate().getTime())/(1000*60*60));
			weiboList.add(weibo);
			weiboListNew.add(weiboNew);
		}//while
		br.close();
	}//process
	
	//输出信息
	public void outPrint()
	{
		for(WeiboInfo i:this.weiboList)
		{
			System.out.println(i.toString());
		}
		
	}

	public ArrayList<WeiboInfo> getWeiboList()
	{
		return weiboList;
	}
	
	public ArrayList<WeiboInfoNew> getWeiboListNew()
	{
		return weiboListNew;
	}
	
}

class WeiboInfo
{
	private int discuss;
	private int transmit;
	private String authorName;
	private String url;
	private String article;
	private Date publishDate;
	private Date insertDate;
	private double timeSubstraction;
	private double entropy;
	private ArrayList<String> ictArticle;
	private double mgh;

	public int getDiscuss()
	{
		return discuss;
	}

	public void setDiscuss(int discuss)
	{
		this.discuss = discuss;
	}

	public int getTransmit()
	{
		return transmit;
	}

	public void setTransmit(int transmit)
	{
		this.transmit = transmit;
	}

	public String getAuthorName()
	{
		return authorName;
	}

	public void setAuthorName(String authorName)
	{
		this.authorName = authorName;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getArticle()
	{
		return article;
	}

	public void setArticle(String article)
	{
		this.article = article;
	}

	public Date getPublishDate()
	{
		return publishDate;
	}

	public void setPublishDate(Date publishDate)
	{
		this.publishDate = publishDate;
	}

	public Date getInsertDate()
	{
		return insertDate;
	}

	public void setInsertDate(Date insertDate)
	{
		this.insertDate = insertDate;
	}
	
	
	public double getTimeSubstraction()
	{
		return timeSubstraction;
	}

	public void setTimeSubstraction(double timeSubstraction)
	{
		this.timeSubstraction = timeSubstraction;
	}

	public double getEntropy()
	{
		return entropy;
	}

	public void setEntropy(double entropy)
	{
		this.entropy = entropy;
	}
	
	public ArrayList<String> getIctArticle()
	{
		return ictArticle;
	}

	public void setIctArticle(ArrayList<String> ictArticle)
	{
		this.ictArticle = ictArticle;
	}
	
	public double getMgh()
	{
		return mgh;
	}

	public void setMgh(double mgh)
	{
		this.mgh = mgh;
	}

	@Override
	public String toString()
	{
		return(this.getArticle()+"\t"+this.getTransmit()+"\t"+this.getDiscuss()+"\t"+this.getPublishDate()+"\t"+
				this.getInsertDate()+"\t"+this.getEntropy()+"\t"+this.getMgh()+"\t"+this.getUrl()+"\t"+this.getAuthorName()
				);
	}

}


class MyDate extends Date
{
	public MyDate(String weiboTime)
	{
		String[] array = weiboTime.split("[^0-9]+");
		for (int i = 0; i < array.length; i++)
		{
			switch (i)
			{
			case 0:
				this.setYear(Integer.parseInt(array[i]) - 1900);
				break;
			case 1:
				this.setMonth(Integer.parseInt(array[i]) - 1);
				break;
			case 2:
				this.setDate(Integer.parseInt(array[i]));
				break;
			case 3:
				this.setHours(Integer.parseInt(array[i]));
				break;
			case 4:
				this.setMinutes(Integer.parseInt(array[i]));
				break;
			case 5:
				this.setSeconds(Integer.parseInt(array[i]));
				break;
			}

		}
	}
	
	@Override
	public String toString()
	{
		return this.toLocaleString();
	}
}


class WeiboInfoNew
{
	private int discuss;
	private int transmit;
	private String authorName;
	private String url;
	private String article;
	private Date publishDate;
	private Date insertDate;
	private double timeSubstraction;
	private double selfInformation;
	private ArrayList<String> ictArticle;
	private double energy;

	public int getDiscuss()
	{
		return discuss;
	}

	public void setDiscuss(int discuss)
	{
		this.discuss = discuss;
	}

	public int getTransmit()
	{
		return transmit;
	}

	public void setTransmit(int transmit)
	{
		this.transmit = transmit;
	}

	public String getAuthorName()
	{
		return authorName;
	}

	public void setAuthorName(String authorName)
	{
		this.authorName = authorName;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getArticle()
	{
		return article;
	}

	public void setArticle(String article)
	{
		this.article = article;
	}

	public Date getPublishDate()
	{
		return publishDate;
	}

	public void setPublishDate(Date publishDate)
	{
		this.publishDate = publishDate;
	}

	public Date getInsertDate()
	{
		return insertDate;
	}

	public void setInsertDate(Date insertDate)
	{
		this.insertDate = insertDate;
	}
	
	
	public double getTimeSubstraction()
	{
		return timeSubstraction;
	}

	public void setTimeSubstraction(double timeSubstraction)
	{
		this.timeSubstraction = timeSubstraction;
	}

	public ArrayList<String> getIctArticle()
	{
		return ictArticle;
	}

	public void setIctArticle(ArrayList<String> ictArticle)
	{
		this.ictArticle = ictArticle;
	}
	
	public double getSelfInformation()
	{
		return selfInformation;
	}

	public void setSelfInformation(double selfInformation)
	{
		this.selfInformation = selfInformation;
	}
	

	public double getEnergy()
	{
		return energy;
	}

	public void setEnergy(double energy)
	{
		this.energy = energy;
	}

	@Override
	public String toString()
	{
		return(this.getArticle()+"\t"+this.getTransmit()+"\t"+this.getDiscuss()+"\t"+this.getPublishDate()+"\t"+
				this.getInsertDate()+"\t"+this.getSelfInformation()+"\t"+this.getEnergy()+"\t"+this.getUrl()+"\t"+this.getAuthorName()
				);
	}

}
