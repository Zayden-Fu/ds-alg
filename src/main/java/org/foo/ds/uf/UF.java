package org.foo.ds.uf;

/**
 * User: fuzongyang
 * Date: 2021/5/5
 * Time: 9:02 PM
 */
public abstract class UF {

	// connected component id
	protected int[] id;

	// number of components
	protected int count;

	public UF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	protected abstract int find(int p);

	protected abstract void union(int p, int q);

}
