package org.foo.ds.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * User: fuzongyang
 * Date: 2021/5/9
 * Time: 9:39 PM
 */
public class BreadthFirstPaths implements IPath {

	private boolean[] marked;
	private int[] edgeTo;
	private final int s;

	public BreadthFirstPaths(Graph g, int s) {
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		this.s = s;
		bfs(g, s);
	}

	private void bfs(Graph g, int s) {
		Deque<Integer> queue = new ArrayDeque<>();
		marked[s] = true;
		queue.add(s);
		while (!queue.isEmpty()) {
			int v = queue.poll();
			for (int w : g.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					marked[w] = true;
					queue.offer(w);
				}
			}
		}
	}

	/**
	 * 是否存在从s到v的路径
	 *
	 * @param v
	 */
	@Override
	public boolean hasPathTo(int v) {
		return marked[v];
	}

	/**
	 * S到V的路径
	 *
	 * @param v
	 */
	@Override
	public Iterable<Integer> pathTo(int v) {
		if (hasPathTo(v)) {
			Deque<Integer> stack = new ArrayDeque<>();
			for (int x = v; x != s; x = edgeTo[x]) {
				stack.push(x);
			}
			stack.push(s);
			return stack;
		}
		return null;
	}
}
