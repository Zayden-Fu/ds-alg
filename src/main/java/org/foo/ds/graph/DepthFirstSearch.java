package org.foo.ds.graph;

/**
 * User: fuzongyang
 * Date: 2021/5/9
 * Time: 6:00 PM
 */
public class DepthFirstSearch implements ISearch {

	private boolean[] marked;
	// 与source的连通次数
	private int count;

	public DepthFirstSearch(Graph g, int s) {
		marked = new boolean[g.V()];
		dfs(g, s);
	}

	/**
	 * 深度优先遍历
	 */
	private void dfs(Graph g, int s) {
		marked[s] = true;
		count++;
		for (int vertex : g.adj(s)) {
			// 未被访问过
			if (!marked(vertex)) {
				dfs(g, vertex);
			}
		}
	}


	/**
	 * source和这个vertex是否连接
	 */
	@Override
	public boolean marked(int v) {
		return marked[v];
	}

	/**
	 * 与source连接的个数
	 */
	@Override
	public int count() {
		return count;
	}
}
