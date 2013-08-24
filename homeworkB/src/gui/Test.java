package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import n_program1.DataProcess1;

import n_program2.DataProcess;
import n_program2.FindShort;
import n_program2.SentenceProcessAndCreateGrphic;

public class Test extends JDialog
{

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			Test dialog = new Test();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws Exception
	 */
	public Test() throws Exception
	{
		DataProcess test1 = readData();

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JTextArea textInput = new JTextArea();
		textInput.setBounds(30, 66, 363, 33);
		textInput.setLineWrap(true);
		contentPanel.add(textInput);

		JTextArea textOutput = new JTextArea();
		textOutput.setBounds(30, 122, 363, 33);
		textOutput.setLineWrap(true);
		contentPanel.add(textOutput);

		{
			JButton button1 = new JButton("\u4E00\u5143\u5206\u8BCD");
			button1.setBounds(63, 196, 93, 23);
			button1.addActionListener(new MyActionListener1(textInput,textOutput));
			contentPanel.add(button1);
		}

		JButton button2 = new JButton("\u4E8C\u5143\u5206\u8BCD");
		button2.setBounds(259, 196, 93, 23);
		button2.addActionListener(new MyActionListener2(test1, textInput,
				textOutput));
		contentPanel.add(button2);

		JLabel lblNewLabel = new JLabel("\u8F93\u5165\u53E5\u5B50");
		lblNewLabel.setBounds(30, 33, 82, 23);
		contentPanel.add(lblNewLabel);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

	public DataProcess readData() throws Exception
	{
		FileInputStream fis = new FileInputStream("序列化保存结果.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		DataProcess test1 = (DataProcess) ois.readObject();

		ois.close();
		return test1;
	}
}

class MyActionListener2 implements ActionListener
{
	private DataProcess test1;
	private SentenceProcessAndCreateGrphic test2;
	private FindShort test3;
	private String result;

	private JTextArea textInput;
	private JTextArea textOutout;

	public MyActionListener2(DataProcess test1, JTextArea textInput,
			JTextArea textOutput) throws Exception
	{

		this.test1 = test1;
		this.textInput = textInput;
		this.textOutout = textOutput;

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

		String input = textInput.getText();

		SentenceProcessAndCreateGrphic test2 = new SentenceProcessAndCreateGrphic(
				input);

		this.test2 = test2;

		test2.setLargeLengthOfWord(test1.getLargeLengthOfWord());
		test2.setWordSet(test1.getWordSet());
		test2.setMapWeight(test1.getMapWeight());
		test2.setLargeWeight(test1.getLargeWeight());

		try
		{
			test2.getAllWordInSentence();
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		test2.createVertexList();
		test2.createGraph();

		FindShort test3 = new FindShort(test2);

		this.test3 = test3;

		test3.Dijkstra();

		this.result = test3.getResult();

		this.textOutout.setText(this.result);
	}
}


class MyActionListener1 implements ActionListener
{
	private JTextArea textInput;
	private JTextArea textOutout;
	
	public MyActionListener1(JTextArea textInput,JTextArea textOutput)
	{
		this.textInput=textInput;
		this.textOutout=textOutput;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String input=textInput.getText();
		
		DataProcess1 test = new DataProcess1(new File("NiuTrans.txt"));
		HashMap<String, Integer> map;
		HashMap<String, Double> mapFrency;

		try
		{
			map = test.doStatistic();
			mapFrency = test.StatisticFrency(map);
		
			double[][] matrix;
			matrix = test.getMatrix(mapFrency, input);
			boolean[][] path = test.dijkstraShotrest(matrix, input);

			String result = test.getResult(path, input);
		
			
			this.textOutout.setText(result);
		
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	
	}


}

