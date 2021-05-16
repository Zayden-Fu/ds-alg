package org.foo.ds.graph;

/**
 * User: fuzongyang
 * Date: 2021/5/16
 * Time: 3:43 PM
 */
public class Topological {

	private Iterable<Integer> order;

	public Topological(DiGraph g) {
		DirectedCycle cycleFinder = new DirectedCycle(g);
		if(!cycleFinder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(g);
			order = dfs.reversePost();
		}
	}

	public Iterable<Integer> order() {
		return order;
	}

	public boolean isDAG() {
		return order != null;
	}

}
