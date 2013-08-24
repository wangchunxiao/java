package edu.fudan.ml.inf.struct;

import java.io.Serializable;
import java.util.Arrays;

import edu.fudan.ml.feature.templet.BaseTemplet;
import edu.fudan.ml.feature.templet.TempletGroup;
import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.Results;

public final class LinearViterbi extends AbstractViterbi {

	private static final long serialVersionUID = -8237762672065700553L;

	private int length;
	private Node[][] lattice;
	private int ysize;
	private TempletGroup templets;
	
	private int[][] data;

	public LinearViterbi(TempletGroup templets, int ysize) {
		this.templets = templets;
		this.ysize = ysize;
	}

	protected void initialLattice(Instance carrier) {
		data = (int[][]) carrier.getData();

		length = data.length;

		buildLattice();

		for (int l = 0; l < length; l++) {
			for (int c = 0; c < ysize; c++) {
				for (int i = 0; i < templets.size(); i++) {
					BaseTemplet t = (BaseTemplet) templets.get(i);
					if (data[l][i] == -1)
						continue;
					if (t.getOrder() == 0) {
						lattice[l][c].base += weights[data[l][i] + c];
					} else if (l > 0 && t.getOrder() == 1) {
						for (int p = 0; p < ysize; p++) {
							int offset = p * ysize + c;
							lattice[l][c].part[p] += weights[data[l][i]
									+ offset];
						}
					}
				}
			}
		}

		for (int c = 0; c < ysize; c++) {
			lattice[0][c].score = lattice[0][c].base;
		}

	}

	private void buildLattice() {
		if (lattice == null || length > lattice.length) {
			lattice = new Node[length][];
			for (int i = 0; i < length; i++) {
				lattice[i] = new Node[ysize];
				for (int j = 0; j < ysize; j++) {
					lattice[i][j] = new Node(ysize);
				}
			}
		} else {
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < ysize; j++)
					lattice[i][j].clear();
			}
		}
	}

	protected void doForwardViterbi(Instance carrier) {
		for (int l = 1; l < length; l++) {
			for (int c = 0; c < ysize; c++) {
				double bestScore = Double.NEGATIVE_INFINITY;
				int bestPath = -1;
				for (int p = 0; p < ysize; p++) {
					double score = lattice[l - 1][p].score + lattice[l][c].base;
					score += lattice[l][c].part[p];
					if (score > bestScore) {
						bestScore = score;
						bestPath = p;
					}
				}
				lattice[l][c].addScore(bestScore, bestPath);
			}
		}
	}

	protected Results<int[]> getPath(int nbest) {
		Results<int[]> res = new Results<int[]>(nbest);
		
		double max = Double.NEGATIVE_INFINITY;
		int cur = 0;
		for (int c = 0; c < ysize; c++) {
			if (lattice[length - 1][c].score > max) {
				max = lattice[length - 1][c].score;
				cur = c;
			}
		}

		for (int n = 0; n < nbest; n++) {
			int[] path = new int[length];
			Arrays.fill(path, -1);
			path[length-1] = cur;
			for (int l = length - 1; l > 0; l--) {
				cur = lattice[l][cur].prev;
				path[l-1] = cur;
			}
			res.addPred(max, path);
		}

		return res;
	}

	private final class Node implements Serializable {

		private static final long serialVersionUID = 6606616807375324479L;

		private double score = 0;
		private int prev = -1;

		private double base = 0;
		private double[] part = null;

		public Node(int n) {
			score = base = 0;
			prev = -1;
			part = new double[n];
		}

		public void addScore(double score, int path) {
			this.score = score;
			this.prev = path;
		}

		public void clear() {
			base = 0;
			score = 0;
			prev = -1;
			Arrays.fill(part, 0);
		}

	}

}
