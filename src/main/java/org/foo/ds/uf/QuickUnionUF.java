package org.foo.ds.uf;

/**
 * User: fuzongyang
 * Date: 2021/5/5
 * Time: 9:22 PM
 */
public class QuickUnionUF extends UF {


	public QuickUnionUF(int N) {
		super(N);
	}

	@Override
	protected int find(int p) {
		while (p != id[p]) {
			p = id[p];
		}
		return p;
	}

	@Override
	protected void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot) {
			return;
		}
		id[pRoot] = qRoot;
		count -- ;
	}
}
