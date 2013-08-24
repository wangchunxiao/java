package edu.fudan.nlp.tag;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import edu.fudan.ml.types.Instance;
import edu.fudan.nlp.tag.Format.Seq2String;

public class CWSTagger extends AbstractTagger {

	public CWSTagger(String str) throws Exception {
		super(str);
	}
	
	public String tag(String src){
		String[] sents = src.split("\n");
		String tag="";
		try {
			for(int i=0;i<sents.length;i++){
				if(sents[i].length()>0){
					Instance inst = new Instance(sents[i]);
					doProcess(inst);
					int[] pred = (int[]) cl.predict(inst).getPredAt(0);
					String[] target = labels.lookupString(pred);
					String s = Seq2String.format(inst, target);
					tag +=s;
				}
				if(i<sents.length-1)
					tag += "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag;
	}

	public static void main(String[] args) throws Exception{
		Options opt = new Options();

		opt.addOption("h", false, "Print help for this application");
		opt.addOption("f", false, "segment file. Default string mode.");
		opt.addOption("s", false, "segment string");
		BasicParser parser = new BasicParser();
		CommandLine cl = parser.parse(opt, args);

		if (args.length == 0 || cl.hasOption('h') ) {
			HelpFormatter f = new HelpFormatter();
			f.printHelp("SEG:\n" +
					"java edu.fudan.nlp.tag.CWSTagger -f model_file input_file output_file;\n" +
					"java edu.fudan.nlp.tag.CWSTagger -s model_file string_to_segement", opt);
			return;
		}		
		String[] arg = cl.getArgs();
		String modelFile;
		String input;
		String output = null;
		if(cl.hasOption("f")&&arg.length==3){
			modelFile = arg[0];
			input = arg[1];
			output = arg[2];
		}else if(arg.length==2){
			modelFile = arg[0];
			input = arg[1];
		}else{
			System.err.println("paramenters format error!");
			System.err.println("Print option \"-h\" for help.");
			return;
		}
		CWSTagger seg = new CWSTagger(modelFile);
		if(cl.hasOption("f")){
			String s = seg.tagFile(input);
			OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(output), "utf8");
			w.write(s);
			w.close();
		}else{
			String s = seg.tag(input);
			System.out.println(s);
		}
	}
	
	
	public String tagFile(String input) {
		StringBuilder res = new StringBuilder();
		try {
			InputStreamReader  read = new InputStreamReader (new FileInputStream(input),"utf-8");
			BufferedReader lbin = new BufferedReader(read);
			String str = lbin.readLine();
			while(str!=null){
				String s= tag(str);
				res.append(s);
				res.append("\n");
				str = lbin.readLine();
			}
			lbin.close();
			return res.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

	}
}
