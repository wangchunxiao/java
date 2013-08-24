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
		 //��ʼ��������������
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
		 
		 //��ʼ��ѭ��,Ĭ���Ҵ�O�Žڵ�(B�ڵ�)��ʼ�����·��
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
			 
			 isFinal[v]=true;//�ҵ����·����
			 
			//�ҵ���̵ĵ�֮�����·��
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
					//"������������,����ίԱ���ֳ��Կ����͵ľ���.");
					"2012�꣬�ڲ��Ĺ����참�����У����м����ص㣺�������ֲ�참����ǿ����ͷ�������ؿ�������Դ�����������ϸ�����������ȫ�����참����һ����߲�참�������ĿƼ���ˮƽ�������ί����첿ע�ش��ŷþٱ���Ѳ�Ӽල���ڰ참����ִ����졢�ල��顢ר���������취����ƻ������Ͱ����Լ�����ý������������з��ְ���������");
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
