package org.foo.ds.graph;

import java.util.ArrayDeque;

/**
 * User: fuzongyang
 * Date: 2021/5/16
 * Time: 1:05 PM
 */
public class DepthFirstOrder {

	private boolean[] marked;
	private ArrayDeque<Integer> preQueue;
	private ArrayDeque<Integer> postQueue;
	private ArrayDeque<Integer> reversePostStack;

	public DepthFirstOrder(DiGraph g) {
		preQueue = new ArrayDeque<>();
		postQueue = new ArrayDeque<>();
		reversePostStack = new ArrayDeque<>();

		marked = new boolean[g.getVertexCount()];

		for (int v = 0; v < g.getVertexCount(); v++) {
			if (!marked[v]) {
				dfs(g, v);
			}
		}
	}

	private void dfs(DiGraph g, int v) {
		preQueue.offer(v);
		marked[v] = true;
		for (int w : g.adj(v)) {
			if (!marked[w]) {
				dfs(g, w);
			}
		}
		postQueue.offer(v);
		reversePostStack.push(v);
	}

	public Iterable<Integer> post() {
		return postQueue;
	}

	public Iterable<Integer> reversePost() {
		return reversePostStack;
	}

}
