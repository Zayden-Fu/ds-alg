package org.foo.ds.graph;

/**
 * User: fuzongyang
 * Date: 2021/5/9
 * Time: 5:53 PM
 */
public interface ISearch {

	/**
	 * source和这个vertex是否连接
	 */
	boolean marked(int v);

	/**
	 * 与source连接的个数
	 */
	int count();

}
