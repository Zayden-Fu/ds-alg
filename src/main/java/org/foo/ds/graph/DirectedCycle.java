package org.foo.ds.graph;

import java.util.Stack;

/**
 * User: fuzongyang
 * Date: 2021/5/16
 * Time: 11:24 AM
 */
public class DirectedCycle {

	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	private boolean[] onStack;

	public DirectedCycle(DiGraph g) {
		onStack = new boolean[g.getVertexCount()];
		edgeTo = new int[g.getVertexCount()];
		marked = new boolean[g.getVertexCount()];
		for (int v = 0; v < g.getVertexCount(); v++) {
			if (!marked[v]) {
				dfs(g, v);
			}
		}
	}

	private void dfs(DiGraph g, int v) {
		onStack[v] = true;
		marked[v] = true;
		for(int w : g.adj(v)) {
			if (hasCycle()) {
				return ;
			} else if (!marked[w]) {
				edgeTo[w] = v;
				dfs(g,w);
			} else if (onStack[w]) {
				cycle = new Stack<>();
				for (int x = v;x != w; x= edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}

	public Iterable<Integer> cycle() {
		return cycle;
	}

	public boolean hasCycle() {
		return cycle != null;
	}
}
