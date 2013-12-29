package auto_aubstract;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClassSentenceDefineInClassTemp
{
	public static void main(String[] args) throws IOException
	{
		File file=new File("ժҪtest2.txt");
		Long length=file.length();
		
		//System.out.println(length);
		
		FileInputStream fis=new FileInputStream(file);
		
	    byte[] contentByte=new byte[length.intValue()];
		
	    fis.read(contentByte,0,length.intValue());
	    
	    String input=new String(contentByte);
	    
		System.out.println(input);
		
		Sentence sentence=new Sentence(input);

		
		String result=sentence.getAllResult();
		System.out.println(result);
		System.out.println("-------------------------------------------------------------");
		
		result=sentence.getNormalizedResult();
		System.out.println(result);
		System.out.println("-------------------------------------------------------------");
		
		result=sentence.getFinalResult(250);
		System.out.println(result);
		System.out.println("-------------------------------------------------------------");
		
		
	}
}
