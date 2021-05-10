package org.foo.ds.graph;

/**
 * User: fuzongyang
 * Date: 2021/5/10
 * Time: 10:05 PM
 */
public class Cycle {

	private boolean[] marked;
	private boolean hasCycle;

	public Cycle(Graph g) {
		marked = new boolean[g.V()];
		for (int v = 0; v < g.V(); v++) {
			if (!marked[v]) {
				dfs(g, v, v);
			}
		}
	}

	private void dfs(Graph g, int v, int u) {
		marked[v] = true;
		for (int w : g.adj(v)) {
			if (!marked[w]) {
				dfs(g, w, v);
			}
			else if (w != u) {
				hasCycle = true;
			}
		}
	}


	public boolean hasCycle() {
		return hasCycle;
	}
}
