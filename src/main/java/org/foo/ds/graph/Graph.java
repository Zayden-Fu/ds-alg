package org.foo.ds.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.List;

/**
 * User: fuzongyang
 * Date: 2021/5/5
 * Time: 6:43 PM
 */
public class Graph {

	private final int V;
	private int E;
	private Bag<Integer>[] adj;

	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<>();
		}
	}

	public Graph(List<String> lines) {
		this(Integer.parseInt(lines.get(0)));
		int E = Integer.parseInt(lines.get(1));
		for (int i = 0; i < E; i++) {
			String[] line = lines.get(i+2).split(" ");
			int v = Integer.parseInt(line[0]);
			int w = Integer.parseInt(line[1]);
			addEdge(v, w);
		}
	}

	public Graph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}


}
