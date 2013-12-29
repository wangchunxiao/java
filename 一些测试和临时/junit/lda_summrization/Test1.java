package lda_summrization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

public class Test1
{

	@Test
	public void test() throws Exception
	{
		File file=new File("./junit/lda_summrization/百度魔图：成名之前与蹿红之后.txt");
		int length=(int) file.length();
		byte[] buffer=new byte[length];
		FileInputStream fis=new FileInputStream(file);
		fis.read(buffer);
		fis.close();
		String content=new String(buffer);
		
		String[] sentences=content.split("[。|！|\n]");
		
		for(String sentence:sentences)
		{
			System.out.println(sentence.trim());
		}
	}

}
