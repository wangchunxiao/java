package edu.fudan.ml.update;

import edu.fudan.ml.types.Instance;

public class LinearMaxPAUpdate extends AbstractPAUpdate {

	protected int diff(Instance inst, double[] weights, Object target,
			Object predict) {

		int[] data = (int[]) inst.getData();
		int gold;
		if (target == null)
			gold = (Integer) inst.getTarget();
		else
			gold = (Integer) target;
		int pred = (Integer) predict;

		for (int i = 0; i < data.length; i++) {
			// int base = data[i];
			// int ts = -1; int ps = -1;
			// ts = base+gold;
			// ps = base+pred;
			//
			// if (ts != -1 && ps != -1) {
			// diffvec.adjustOrPutValue(ts, 1, 1);
			// diffvec.adjustOrPutValue(ps, -1, -1);
			// diffw += weights[ts] - weights[ps];
			// }
			if (data[i] != -1) {
				int ts = data[i] + gold;
				int ps = data[i] + pred;
				diffvec.adjustOrPutValue(ts, 1, 1);
				diffvec.adjustOrPutValue(ps, -1, -1);
				diffw += weights[ts]-weights[ps];
			}
		}

		return 1;
	}

}
