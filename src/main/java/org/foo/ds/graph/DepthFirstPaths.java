package org.foo.ds.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * User: fuzongyang
 * Date: 2021/5/9
 * Time: 7:02 PM
 */
public class DepthFirstPaths implements IPath {

	private boolean[] marked;
	private int[] edgeTo;
	private final int s;

	public DepthFirstPaths(Graph g, int s) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		this.s = s;
		dfs(g, s);
	}

	private void dfs(Graph g, int v) {
		marked[v] = true;
		for (int w : g.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(g, w);
			}
		}
	}

	/**
	 * 是否存在从s到v的路径
	 */
	@Override
	public boolean hasPathTo(int v) {
		return marked[v];
	}

	/**
	 * S到V的路径
	 */
	@Override
	public Iterable<Integer> pathTo(int v) {
		if (hasPathTo(v)) {
			Deque<Integer> deque = new ArrayDeque<>();
			for (int x = v; x != s; x = edgeTo[x]) {
				deque.push(x);
			}
			deque.push(s);
			return deque;
		}
		return null;
	}
}
