package org.foo.ds.graph;

import java.util.LinkedList;

/**
 * 有向图
 * <p>
 * User: fuzongyang
 * Date: 2021/5/12
 * Time: 10:44 PM
 */
public class DiGraph {

	private final int vertexCount;
	private int edgeCount;
	private LinkedList<Integer>[] adj;

	public DiGraph(int vertexCount) {
		this.vertexCount = vertexCount;
		this.edgeCount = 0;
		adj = new LinkedList[vertexCount];
		for (int v = 0; v < vertexCount; v++) {
			adj[v] = new LinkedList<>();
		}
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public int getEdgeCount() {
		return edgeCount;
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		edgeCount++;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	public DiGraph reverse() {
		DiGraph diGraph = new DiGraph(vertexCount);
		for (int v = 0; v < vertexCount; v++) {
			for (int w : adj(v)) {
				diGraph.addEdge(w, v);
			}
		}
		return diGraph;
	}

}
