package org.foo.ds.graph;

/**
 * User: fuzongyang
 * Date: 2021/5/9
 * Time: 6:56 PM
 */
public interface IPath {

	/**
	 * 是否存在从s到v的路径
	 */
	boolean hasPathTo(int v);

	/**
	 * S到V的路径
	 */
	Iterable<Integer> pathTo(int v);


}
