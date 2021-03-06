package org.foo.ds.graph;

/**
 * User: fuzongyang
 * Date: 2021/5/10
 * Time: 8:59 PM
 */
public class CC {

	private boolean[] marked;
	private int[] id;
	private int count;

	public CC(Graph g) {
		marked = new boolean[g.V()];
		id = new int[g.V()];
		for (int s = 0; s < g.V(); s++) {
			if (!marked[s]) {
				dfs(g, s);
				count++;
			}
		}
	}

	private void dfs(Graph g, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : g.adj(v)) {
			if (!marked[w]) {
				dfs(g, w);
			}
		}
	}

	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}

	public int id(int v) {
		return id[v];
	}

	public int count() {
		return count;
	}

}
