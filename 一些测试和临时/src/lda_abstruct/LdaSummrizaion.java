package lda_abstruct;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import LDA.MAIN.Documents;
import LDA.MAIN.LdaGibbsSampling;
import LDA.MAIN.LdaModel;

public class LdaSummrizaion
{
	public void outputResult(ArrayList<ArrayList<String>> result,
			ArrayList<String> filename, String outputPath) throws Exception
	{
		File path=new File(outputPath);
		
		for(int i=0; i<result.size(); i++)
		{
			File f=new File(path,filename.get(i));
			BufferedWriter bw=new BufferedWriter(new FileWriter(f));
			for(String temp:result.get(i))
			{
				bw.write(temp +"\n");
			}
			bw.close();
		}
	}

	public static void main(String[] args) throws Exception
	{
		// test this class
		// testLdaGibbsSamplingClass();

		// lda main code
		LdaGibbsSampling.modelparameters para = new LdaGibbsSampling.modelparameters();
		LdaGibbsSampling.getParametersFromFile(para,
				"./src/lda_abstruct/parameter.conf");
		LdaModel ldaModel = new LdaModel(para);
		Documents documents = new Documents();
		documents.readDocs("./src/lda_abstruct/corpus1",
				"./src/lda_abstruct/result/filename.txt");
		documents.serializable4Inference("./src/lda_abstruct/serializable/",
				"indexToTermMap.ser");

		System.out.println("1 Initialize the model ...");
		ldaModel.initializeModel(documents);
		System.out.println("2 Learning and Saving the model ...");
		ldaModel.inferenceModel(documents, "./src/LDA/MAIN/result/");
		System.out.println("3 Output the final model ...");

		ldaModel.saveIteratedModel(para.iteration, documents,
				"./src/lda_abstruct/result/");
		ldaModel.savePhi(para.iteration, documents,
				"./src/lda_abstruct/result/");
		ldaModel.saveTheta(para.iteration, documents,
				"./src/lda_abstruct/result/");
		ldaModel.serializablePhi("./src/lda_abstruct/serializable/", "phi.ser");
		System.out.println("Done!");

		System.out.println("-------------------------------------------------");
		Documents4Sumrization documents4Sumrization = new Documents4Sumrization(
				documents, ldaModel);
		ArrayList<ArrayList<String>> result = documents4Sumrization.readDocs(
				"./src/lda_abstruct/corpus1", 200);
		
		LdaSummrizaion ldaSummrization=new LdaSummrizaion();
		ldaSummrization.outputResult(result,documents4Sumrization.filename,"./src/lda_abstruct/abstruct_result/");
		System.out.println("ALL DONE");
		
	}
}
