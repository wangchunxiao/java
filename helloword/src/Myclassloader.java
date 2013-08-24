/*我们自己定义一个类加载器
 * 1.类加载器的父子关系(不是继承的父子关系)  
 * 根加载器(C++实现)--->扩展加载器(在jre的目录下面的那个地方的jar包被扩展加载器加载)--->系统加载器(就是classpath环境变量
 * 下的类都是这个加载器加载)
 * 注意，这里扩展加载器和系统加载器都是继承自ClassLoader类
 * 2.对我们自定义的类加载器，我们只需要重写ClassLoader的findClass()方法
 * 3.我们分析ClassLoader的源代码，特别是loadClass方法，我们可以发现加载器的两个规则，一个是父委托机制，另一个是一次加载机制
 *   对于父委托机制，就是如果我们制定某个加载器加载这个类，如果这个加载器的父加载器能加载这个类，就用父加载器加载，如果爷爷可以
 *   加载，就用爷爷，以此类推，知道遇到不能加载的加载器，永远是最上层能加载这个类的加载器对这个类进行加载
 *   对于一次加载机制，就是对于同一个类，如果是同一个加载器，或者两个有父子关系的加载器
 * */

/*
 * 1. 这个列子中，我们设置了一个自己的加载器，并设置了加载路径，重写了findclass方法并用了defineClass方法将一个byte[]转化
 * 为一个Class对象。
 *2.在实例中，我们看到了加载顺序，所谓的加载器就是在加载目录中找寻.class文件并读入内存，加载顺序遵循父委托机制
 *3.根加载器加载java.lang包，扩展加载器加载jre中的某个包，而系统加载器加载classpath所对应的路径，我们看到classpath中有个‘.’
 *  表示当前目录，这也就是为什么我们写的程序都是默认的被系统加载器加载的原因
 *4.命名空间的问题，我们这里加载了两个dog类，而且都是用的不同的类加载器，这是可以的，因为loader1和loader3的命名空间是不同
 *  的，这两个dog类分别属于不同的命名空间，彼此不可见
 *5.就第4点有个疑问，为什么我们即使把dog类放到classpath下面，而test(loader3)的时候还是用的loader3而没用系统加载器
 *  乍一看是和父亲委托是矛盾的，其实不然，因为我们第一次test的时候，dog被系统加载器加载。而第二次test的时候，命名空间
 *  只有loader3和根加载器，这个命名空间内没有系统加载器，我们的程序要让两个类访问即可见，则只能是loader3加载，保证
 *  了它们在一个命名空间下它们才能可见
 *6.关于5的可见，儿子的能看见父亲的类，而父亲的看不见儿子加载器加载的类(要是能看见，也就不用儿子加载了，就父委托父亲加载了)
 *  不同命名空间，绝对不可见
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

	// 这里是根据那个API文档写的，我们要重载findClass方法，而API重载的方法中就有调用这个函数从
	// 硬盘中读入一个.class文件到内存的byte[]数组中去
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

			// 将FileInputStream的东西输入到一个ByteArrayOutputStream中去
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
	// 这个函数就是将我们从硬盘读入的.class文件到内存的byte[]数组中后，将byte[]转化成为一个Class对象
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
		
		/*下面这个例子就和可见性有关，我们不考虑loader3，只考虑这个类和loader1,loader2
		 * 
		 * Class<?> clazz=loader2.loadClass("Sample");
		 * Object object=clazz.newInstance();//显然这个是Sample类型的
		 * Sample sample=(Sample)object;//问题就出现在这，我们这个类是classpath加载的，而Sample是loader1加载的
		 * 								//父亲看不见儿子，在这个类里自然就看不到Sample类，解决方法用反射调用方法
		 * sample.dosomething();															
		 * */
	}
	
	public static void test(ClassLoader loader) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Class<?> clazz=loader.loadClass("Sample");
		Object object=clazz.newInstance();
	}
}
