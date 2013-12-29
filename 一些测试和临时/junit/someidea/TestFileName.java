package someidea;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

public class TestFileName
{
	
	File file=new File("E:\\¿¨ª¯\\≤‚ ‘”√");
	
	@Test
	public void test()
	{
		File[] filenames=file.listFiles();
		for(File file:filenames)
			System.out.println(file);
		
	}

}
