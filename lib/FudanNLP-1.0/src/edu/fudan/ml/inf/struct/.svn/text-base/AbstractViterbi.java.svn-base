package edu.fudan.ml.inf.struct;

import edu.fudan.ml.inf.Inferencer;
import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.Results;

public abstract class AbstractViterbi extends Inferencer {

	private static final long serialVersionUID = 2627448350847639460L;

	public Results getBest(Instance inst) {
		return getBest(inst, 1);
	}
	
	public synchronized Results getBest(Instance carrier, int nbest)	{

		initialLattice(carrier);
		
		doForwardViterbi(carrier);
		
		Results res = getPath(nbest);

		return res;
	}
	
	protected abstract Results getPath(int nbest);
	
	protected abstract void initialLattice(Instance carrier);
	
	protected abstract void doForwardViterbi(Instance carrier);
}
