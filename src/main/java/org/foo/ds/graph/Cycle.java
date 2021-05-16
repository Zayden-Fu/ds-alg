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
			// 1、此vertex的领近vertex已经访问过
			// 2、不是dfs上一级的vertex
			// 说明此vertex和已访问过的其它vertex相连，也就是有环图
			else if (w != u) {
				hasCycle = true;
			}
		}
	}


	public boolean hasCycle() {
		return hasCycle;
	}
}
