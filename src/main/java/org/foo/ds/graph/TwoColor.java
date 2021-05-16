package org.foo.ds.graph;

/**
 * User: fuzongyang
 * Date: 2021/5/11
 * Time: 10:21 PM
 */
public class TwoColor {

	private boolean[] marked;
	private boolean[] color;
	private boolean isTwoColorable = true;

	public TwoColor(Graph g) {
		marked = new boolean[g.V()];
		color = new boolean[g.V()];
		for (int s = 0; s < g.V(); s++) {
			if (!marked[s]) {
				dfs(g, s);
			}
		}

	}

	private void dfs(Graph g, int v) {
		marked[v] = true;
		for (int vertex : g.adj(v)) {
			if (!marked[vertex]) {
				color[vertex] = !color[v];
				dfs(g, vertex);
			}
			// 如果相等 则设置为false
			else if (color[vertex] == color[v]) {
				isTwoColorable = false;
			}
		}
	}

	public boolean isBipartite() {
		return isTwoColorable;
	}

}
