package org.foo.ds.tree;

import org.junit.jupiter.api.Test;

/**
 * User: fuzongyang
 * Date: 2021/5/4
 * Time: 5:35 PM
 */
public class RedBlackTreeTest {

	/**
	 * 测试遍历器
	 */
	@Test
	void test_red_black_tree_with_Iterable() {
		RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();
		redBlackTree.add(1);
		redBlackTree.add(5);
		redBlackTree.add(2);
		redBlackTree.add(10);
		redBlackTree.add(7);
		redBlackTree.add(9);
		redBlackTree.add(8);
		redBlackTree.add(3);
		redBlackTree.add(6);
		Iterable<Integer> integers = redBlackTree.elements();
		integers.forEach(e -> System.out.print(e + ","));

		System.out.println("-----");

		System.out.println(redBlackTree.contains(3));

//		redBlackTree.deleteMin();
		redBlackTree.deleteMax();
//		redBlackTree.delete(7);

		redBlackTree.elements().forEach(e -> System.out.print(e + ","));

	}

}
