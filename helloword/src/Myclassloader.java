/*�����Լ�����һ���������
 * 1.��������ĸ��ӹ�ϵ(���Ǽ̳еĸ��ӹ�ϵ)  
 * ��������(C++ʵ��)--->��չ������(��jre��Ŀ¼������Ǹ��ط���jar������չ����������)--->ϵͳ������(����classpath��������
 * �µ��඼���������������)
 * ע�⣬������չ��������ϵͳ���������Ǽ̳���ClassLoader��
 * 2.�������Զ�����������������ֻ��Ҫ��дClassLoader��findClass()����
 * 3.���Ƿ���ClassLoader��Դ���룬�ر���loadClass���������ǿ��Է��ּ���������������һ���Ǹ�ί�л��ƣ���һ����һ�μ��ػ���
 *   ���ڸ�ί�л��ƣ�������������ƶ�ĳ����������������࣬�������������ĸ��������ܼ�������࣬���ø����������أ����үү����
 *   ���أ�����үү���Դ����ƣ�֪���������ܼ��صļ���������Զ�����ϲ��ܼ��������ļ��������������м���
 *   ����һ�μ��ػ��ƣ����Ƕ���ͬһ���࣬�����ͬһ�������������������и��ӹ�ϵ�ļ�����
 * */

/*
 * 1. ��������У�����������һ���Լ��ļ��������������˼���·������д��findclass����������defineClass������һ��byte[]ת��
 * Ϊһ��Class����
 *2.��ʵ���У����ǿ����˼���˳����ν�ļ����������ڼ���Ŀ¼����Ѱ.class�ļ��������ڴ棬����˳����ѭ��ί�л���
 *3.������������java.lang������չ����������jre�е�ĳ��������ϵͳ����������classpath����Ӧ��·�������ǿ���classpath���и���.��
 *  ��ʾ��ǰĿ¼����Ҳ����Ϊʲô����д�ĳ�����Ĭ�ϵı�ϵͳ���������ص�ԭ��
 *4.�����ռ�����⣬�����������������dog�࣬���Ҷ����õĲ�ͬ��������������ǿ��Եģ���Ϊloader1��loader3�������ռ��ǲ�ͬ
 *  �ģ�������dog��ֱ����ڲ�ͬ�������ռ䣬�˴˲��ɼ�
 *5.�͵�4���и����ʣ�Ϊʲô���Ǽ�ʹ��dog��ŵ�classpath���棬��test(loader3)��ʱ�����õ�loader3��û��ϵͳ������
 *  էһ���Ǻ͸���ί����ì�ܵģ���ʵ��Ȼ����Ϊ���ǵ�һ��test��ʱ��dog��ϵͳ���������ء����ڶ���test��ʱ�������ռ�
 *  ֻ��loader3�͸�����������������ռ���û��ϵͳ�����������ǵĳ���Ҫ����������ʼ��ɼ�����ֻ����loader3���أ���֤
 *  ��������һ�������ռ������ǲ��ܿɼ�
 *6.����5�Ŀɼ������ӵ��ܿ������׵��࣬�����׵Ŀ��������Ӽ��������ص���(Ҫ���ܿ�����Ҳ�Ͳ��ö��Ӽ����ˣ��͸�ί�и��׼�����)
 *  ��ͬ�����ռ䣬���Բ��ɼ�
 * */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Myclassloader extends ClassLoader
{
	private String name;
	private String path;
	private final String fileType = ".class";

	public Myclassloader(String name)
	{
		super();
		this.name = name;
	}

	public Myclassloader(ClassLoader parent,  String name)
	{
		super(parent);
		this.name = name;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return this.name;
	}

	// �����Ǹ����Ǹ�API�ĵ�д�ģ�����Ҫ����findClass��������API���صķ����о��е������������
	// Ӳ���ж���һ��.class�ļ����ڴ��byte[]������ȥ
	private byte[] loadClassData(String name)
	{
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream baos = null;

		try
		{
			this.name = this.name.replace('.', '\\');
			is = new FileInputStream(new File(path + name + fileType));
			baos = new ByteArrayOutputStream();

			// ��FileInputStream�Ķ������뵽һ��ByteArrayOutputStream��ȥ
			int ch = 0;
			while (-1 != (ch = is.read()))
			{
				baos.write(ch);
			}

			data = baos.toByteArray();

		} catch (Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			try
			{
				is.close();
				baos.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return data;
	}

	@Override
	// ����������ǽ����Ǵ�Ӳ�̶����.class�ļ����ڴ��byte[]�����к󣬽�byte[]ת����Ϊһ��Class����
	protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		byte[] data = this.loadClassData(name);
		return this.defineClass(data, 0, data.length);
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Myclassloader loader1 = new Myclassloader("loader1");

		loader1.setPath("E:\\java project\\classloader_test\\serverlib\\");

		Myclassloader loader2 = new Myclassloader(loader1, "loader2");

		loader2.setPath("E:\\java project\\classloader_test\\clientlib\\");

		Myclassloader loader3 = new Myclassloader(null, "loader3");

		loader3.setPath("E:\\java project\\classloader_test\\otherlib\\");
		
		test(loader2);
		System.out.println("------------------");
		test(loader3);
		System.out.println("-------------------");
		
		/*����������ӾͺͿɼ����йأ����ǲ�����loader3��ֻ����������loader1,loader2
		 * 
		 * Class<?> clazz=loader2.loadClass("Sample");
		 * Object object=clazz.newInstance();//��Ȼ�����Sample���͵�
		 * Sample sample=(Sample)object;//����ͳ������⣬�����������classpath���صģ���Sample��loader1���ص�
		 * 								//���׿��������ӣ������������Ȼ�Ϳ�����Sample�࣬��������÷�����÷���
		 * sample.dosomething();															
		 * */
	}
	
	public static void test(ClassLoader loader) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Class<?> clazz=loader.loadClass("Sample");
		Object object=clazz.newInstance();
	}
}
