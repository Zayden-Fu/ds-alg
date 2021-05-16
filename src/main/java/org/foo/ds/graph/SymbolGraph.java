package org.foo.ds.graph;

import java.util.HashMap;
import java.util.List;

/**
 * User: fuzongyang
 * Date: 2021/5/11
 * Time: 9:32 PM
 */
public class SymbolGraph {

	private HashMap<String, Integer> st;
	private String[] keys;
	private Graph g;

	/**
	 * @param lines 文件流里的行集合
	 * @param sp    每一行中两个vertex的分隔符
	 */
	public SymbolGraph(List<String> lines, String sp) {
		// 先构建st索引
		st = new HashMap<>();
		for (String line : lines) {
			String[] vertexes = line.split(sp);
			for (String vertex : vertexes) {
				// 使用st.size()作为索引
				st.putIfAbsent(vertex, st.size());
			}
		}
		// 构建反向索引
		keys = new String[st.size()];
		for (String key : st.keySet()) {
			keys[st.get(key)] = key;
		}
		// 构建图
		g = new Graph(st.size());
		for (String line : lines) {
			String[] vertexes = line.split(sp);
			// 找到vertex的数组索引
			int v = st.get(vertexes[0]);
			for (int i = 1; i < vertexes.length; i++) {
				g.addEdge(v, st.get(vertexes[i]));
			}
		}
	}

	public boolean contains(String s) {
		return st.containsKey(s);
	}

	public int index(String s) {
		return st.get(s);
	}

	public String name(int v) {
		return keys[v];
	}

	public Graph g() {
		return g;
	}
}
