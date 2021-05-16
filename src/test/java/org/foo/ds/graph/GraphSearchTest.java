package org.foo.ds.graph;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: fuzongyang
 * Date: 2021/5/9
 * Time: 6:15 PM
 */
public class GraphSearchTest {

	@Test
	void test_dps_search() throws IOException {
		Path path = Paths.get("./build/resources/main", "alg4-data/tinyG.txt");
		List<String> lines = Files.readAllLines(path);
		Graph g = new Graph(lines);
		ISearch search = new DepthFirstSearch(g, 0);
		for (int v = 0; v < g.V(); v++) {
			if (search.marked(v)) {
				System.out.print(v + " ");
			}
		}
		System.out.println();
		if (search.count() != g.V()) {
			System.out.print("NOT ");
		}
		System.out.print("Connected");
		System.out.println();
	}

	@Test
	void test_dps_search_path() throws IOException {
		Path path = Paths.get("./build/resources/main", "alg4-data/tinyCG.txt");
		List<String> lines = Files.readAllLines(path);
		Graph g = new Graph(lines);
		int source = 0;
		DepthFirstPaths search = new DepthFirstPaths(g, source);
		for (int v = 0; v < g.V(); v++) {
			System.out.print(source + " to" + v + ": ");
			if (search.hasPathTo(v)) {
				for (int x : search.pathTo(v)) {
					if (x == source) {
						System.out.print(x);
					} else {
						System.out.print("-" + x);
					}
				}
			}
			System.out.println();
		}
	}

	@Test
	void test_bps_search_path() throws IOException {
		Path path = Paths.get("./build/resources/main", "alg4-data/tinyCG.txt");
		List<String> lines = Files.readAllLines(path);
		Graph g = new Graph(lines);
		int source = 0;
		BreadthFirstPaths search = new BreadthFirstPaths(g, source);
		for (int v = 0; v < g.V(); v++) {
			System.out.print(source + " to" + v + ": ");
			if (search.hasPathTo(v)) {
				for (int x : search.pathTo(v)) {
					if (x == source) {
						System.out.print(x);
					} else {
						System.out.print("-" + x);
					}
				}
			}
			System.out.println();
		}
	}

	@Test
	void test_dfs_cc() throws IOException {
		Path path = Paths.get("./build/resources/main", "alg4-data/tinyG.txt");
		List<String> lines = Files.readAllLines(path);
		Graph g = new Graph(lines);
		CC cc = new CC(g);
		int m = cc.count();
		System.out.println(m + " components");

		LinkedList<Integer>[] components = new LinkedList[m];

		for (int i = 0; i < m; i++) {
			components[i] = new LinkedList<Integer>();
		}

		for (int v = 0; v < g.V(); v++) {
			components[cc.id(v)].add(v);
		}

		for(int i = 0 ;i<m;i++){
			for(int v : components[i]) {
				System.out.print(v + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 测试有没有环
	 */
	@Test
	void test_has_cycle() throws IOException {
		List<String> lines = new ArrayList<>();
		lines.add("5");
		lines.add("4");
		lines.add("0 1");
		lines.add("0 2");
		lines.add("1 2");
		lines.add("3 4");

		Graph g = new Graph(lines);
		Cycle cycle = new Cycle(g);
		System.out.println(cycle.hasCycle());
	}

	/**
	 * 测试符号图
	 */
	@Test
	void test_symbol_graph(){

	}

}
