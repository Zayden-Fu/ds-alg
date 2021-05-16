package org.foo.ds.graph;

/**
 * User: fuzongyang
 * Date: 2021/5/12
 * Time: 11:19 PM
 */
public class DirectedDFS {

	private boolean[] marked;

	public DirectedDFS(DiGraph g, int s) {
		marked = new boolean[g.getVertexCount()];
		dfs(g, s);
	}

	public DirectedDFS(DiGraph g, Iterable<Integer> sources) {
		marked = new boolean[g.getVertexCount()];
		for (int s : sources) {
			if (!marked[s]) {
				dfs(g, s);
			}
		}
	}

	private void dfs(DiGraph g, int s) {
		marked[s] = true;
		for (int w : g.adj(s)) {
			if (!marked[w]) {
				dfs(g, w);
			}
		}
	}
}
