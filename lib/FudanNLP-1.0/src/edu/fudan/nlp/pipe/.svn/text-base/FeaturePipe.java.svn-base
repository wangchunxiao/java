package edu.fudan.nlp.pipe;

import java.util.Iterator;
import java.util.List;

import edu.fudan.ml.types.FeatureAlphabet;
import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.LabelAlphabet;

public class FeaturePipe extends Pipe {

	private FeatureAlphabet features;
	private int idx=0;
	private LabelAlphabet label;

	public FeaturePipe(FeatureAlphabet features, LabelAlphabet la) {
		this.features = features;
		this.label = la;
		// 增加常数项
		idx = features.lookupIndex("#@$");
	}
	
	@Override
	public void addThruPipe(Instance inst) throws Exception {
		List<String> data = (List<String>) inst.getData();
		int size = data.size();
		int[] newdata = new int[data.size()+1];
		Iterator<String> it = data.iterator();
		
		for(int i=0;i<size;i++){
			String token = it.next();
			int id = features.lookupIndex(token,label.size());
			if(id==-1)
				continue;
			newdata[i] = id;
		}
		newdata[size]=idx;
		inst.setData(newdata);
	}

}
