package org.foo.ds.graph;

import org.junit.jupiter.api.Test;

/**
 * User: fuzongyang
 * Date: 2021/5/16
 * Time: 12:23 PM
 */
public class DiGraphTest {

	/**
	 * 测试一个有向图中是否有环
	 * 采用dfs方式实现
	 */
	@Test
	void test_digraph_has_cycle() {
		DiGraph diGraph = new DiGraph(4);
		diGraph.addEdge(0, 1);
		diGraph.addEdge(1, 2);
		diGraph.addEdge(2, 3);
		diGraph.addEdge(3, 1);

		DirectedCycle cycle = new DirectedCycle(diGraph);
		System.out.println(cycle.hasCycle() ? "Yes , has Cycle" : "No Cycle");
	}

	/**
	 * 测试有向图的各种遍历方式
	 */
	@Test
	void test_digraph_traversal() {
		DiGraph diGraph = new DiGraph(13);
		diGraph.addEdge(0, 1);
		diGraph.addEdge(0, 5);
		diGraph.addEdge(0, 6);
		diGraph.addEdge(2, 0);
		diGraph.addEdge(2, 3);
		diGraph.addEdge(3, 5);
		diGraph.addEdge(5, 4);
		diGraph.addEdge(6, 4);
		diGraph.addEdge(6, 9);
		diGraph.addEdge(7, 6);
		diGraph.addEdge(8, 7);
		diGraph.addEdge(9, 10);
		diGraph.addEdge(9, 11);
		diGraph.addEdge(9, 12);
		diGraph.addEdge(11, 12);

		DepthFirstOrder order = new DepthFirstOrder(diGraph);
		for (int e : order.post()){
			System.out.print(e + " ");
		}
		System.out.println();
		System.out.println("----");
		for (int e : order.reversePost()){
			System.out.print(e + " ");
		}
		System.out.println();
	}

	@Test
	void test_kosaraju(){
		DiGraph diGraph = new DiGraph(13);
		diGraph.addEdge(0, 1);
		diGraph.addEdge(0, 5);
		diGraph.addEdge(2, 0);
		diGraph.addEdge(2, 3);
		diGraph.addEdge(3, 2);
		diGraph.addEdge(3, 5);
		diGraph.addEdge(4, 3);
		diGraph.addEdge(4, 2);
		diGraph.addEdge(5, 4);
		diGraph.addEdge(6, 0);
		diGraph.addEdge(6, 4);
		diGraph.addEdge(6, 9);
		diGraph.addEdge(7, 6);
		diGraph.addEdge(7, 8);
		diGraph.addEdge(8, 7);
		diGraph.addEdge(8, 9);
		diGraph.addEdge(9, 10);
		diGraph.addEdge(9, 11);
		diGraph.addEdge(10, 12);
		diGraph.addEdge(11, 4);
		diGraph.addEdge(11, 12);
		diGraph.addEdge(12, 9);

		KosarajuSCC scc = new KosarajuSCC(diGraph);
		System.out.println(scc.count());
	}

}
