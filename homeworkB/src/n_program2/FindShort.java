package n_program2;

import java.io.File;
import java.util.ArrayList;

public class FindShort 
{
	 private String sentence;
	 private double[][] matrix;
	 private boolean[][] path;
	 private boolean[] isFinal;
	 private double[] shortLengthEveryVertex;
	 private int vertexNumber;
	 private static final double INFINITY=1000000;
	 
	 private ArrayList<VertexType> vertexList;
	 
	 public FindShort(SentenceProcessAndCreateGrphic instance)
	 {
		matrix=instance.getMatrix();
		vertexList=instance.getVertexList();
		vertexNumber=vertexList.size();
		
		path=new boolean[vertexNumber][vertexNumber];
		isFinal=new boolean[vertexNumber];
		shortLengthEveryVertex=new double[vertexNumber];
		
		this.sentence=instance.getSentence();
	 }
	 
	 public void Dijkstra()
	 {
		 //初始化上面三个数组
		 for(int i=0;i<vertexNumber;i++)
		 {
			 isFinal[i]=false;
			 shortLengthEveryVertex[i]=matrix[0][i];
			 for(int j=0;j<vertexNumber;j++)
			 {
				 path[i][j]=false;
			 }
			 if(shortLengthEveryVertex[i]<INFINITY)
			 {
				 path[0][i]=true;
			 }
		 }
		 
		 //开始主循环,默认找从O号节点(B节点)开始的最短路径
		 for(int i=1;i<vertexNumber;i++)
		 {
			 double shortLength=INFINITY;
			 int v=-1;
			 for(int j=0;j<vertexNumber;j++)
			 {
				 if(!isFinal[j])
				 {
					 if(shortLength>shortLengthEveryVertex[j])
					 {
						 shortLength=shortLengthEveryVertex[j];
						 v=j;
					 }
				 }
			 } 
			 
			 isFinal[v]=true;//找到最短路径点
			 
			//找到最短的点之后更新路径
			 for(int j=0;j<vertexNumber;j++)
			 {
				 if(!isFinal[j] && (shortLength + matrix[v][j]<shortLengthEveryVertex[j]))
				 {
					 shortLengthEveryVertex[j]=shortLength + matrix[v][j];
					 for(int k=0;k<vertexNumber;k++)
					 {
						 path[j][k]=path[v][k];
					 }
					 path[j][v]=true;
				 }
			 }
		 }
	 }//Dijkstra()
	 
	 public String getResult()
	 {
		 String result=new String();
		 VertexType[] vertexArray=vertexList.toArray(new VertexType[vertexList.size()]);
		 for(int j=0;j<vertexNumber;j++)
		 {
			 if(path[vertexNumber-1][j])
			 {
				 result+=vertexArray[j].getToken()+"/";
			 }
		 } 
		 result=result.substring(0, result.length()-1);
		 return result;
	 }
	 
	 public String outpurMircosoftResult()
	 {
		 String result=new String();
		 VertexType[] vertexArray=vertexList.toArray(new VertexType[vertexList.size()]);
		 for(int j=0;j<vertexNumber;j++)
		 {
			 if(path[vertexNumber-1][j])
			 {
				 result+=vertexArray[j].getToken()+"  ";
			 }
		 } 
		 result=result.substring(0, result.length()-2);
		 return result;
	 }
	 
	 public static void main(String[] args) throws Exception 
	 {
		 	DataProcess test1 = new DataProcess(new File("NiuTrans.txt"));
			test1.doStatistic();
			test1.doStatisticWeight();
			System.out.println("-------------------------------------");
			
			SentenceProcessAndCreateGrphic test2 = new SentenceProcessAndCreateGrphic(
					//"在宣讲过程中,宣传委员表现出吃苦耐劳的精神.");
					"2012年，在查办的工作办案过程中，还有几大特点：继续保持查办案件的强劲势头，不断拓宽案件的来源渠道，严肃严格依纪依法安全文明办案，进一步提高查办案件工作的科技化水平。中央纪委、监察部注重从信访举报、巡视监督、在办案件、执法监察、监督检查、专项治理、公检法和审计机关移送案件以及新闻媒体和网络舆情中发现案件线索。");
			test2.setLargeLengthOfWord(test1.getLargeLengthOfWord());
			test2.setWordSet(test1.getWordSet());
			test2.setMapWeight(test1.getMapWeight());
			test2.setLargeWeight(test1.getLargeWeight());
			test2.getAllWordInSentence();
			test2.createVertexList();
			test2.createGraph();
			test2.outputMatrixInCowAndLowNumber();
			System.out.println("-------------------------------------");
			
			FindShort test3=new FindShort(test2);
			test3.Dijkstra();
			System.out.println(test3.getResult());
	 }
	 
}
