package org.foo.ds.graph;

/**
 * User: fuzongyang
 * Date: 2021/5/16
 * Time: 5:41 PM
 */
public class KosarajuSCC {

	private boolean[] marked;
	private int[] id;
	private int count;

	public KosarajuSCC(DiGraph g) {
		marked = new boolean[g.getVertexCount()];
		id = new int[g.getVertexCount()];
		DepthFirstOrder order = new DepthFirstOrder(g.reverse());
		for (int s : order.reversePost()) {
			if (!marked[s]) {
				dfs(g, s);
				count++;
			}
		}
	}

	private void dfs(DiGraph g, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : g.adj(v)) {
			if (!marked[w]) {
				dfs(g, w);
			}
		}
	}

	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}

	public int id(int v) {
		return id[v];
	}

	public int count() {
		return count;
	}
}
