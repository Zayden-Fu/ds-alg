package org.foo.ds.uf;

/**
 * User: fuzongyang
 * Date: 2021/5/5
 * Time: 9:12 PM
 */
public class QuickFindUF extends UF {

	public QuickFindUF(int N) {
		super(N);
	}

	@Override
	protected int find(int p) {
		return id[p];
	}

	@Override
	protected void union(int p, int q) {
		int pId = find(p);
		int qId = find(q);
		if (pId == qId) {
			return;
		}
		// Rename p's component to q's name;
		for (int i = 0; i < id.length; i++) {
			if (id[i] == pId) {
				id[i] = qId;
			}
		}
		// connected component count -1
		count--;
	}
}
