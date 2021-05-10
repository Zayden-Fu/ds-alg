package org.foo.ds.tree;

import org.junit.jupiter.api.Test;

/**
 * User: fuzongyang
 * Date: 2021/5/4
 * Time: 4:14 PM
 */
public class BinarySearchTreeTest {

	/**
	 * 测试遍历器
	 */
	@Test
	void test_binary_search_tree_with_Iterable(){
		BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
		binarySearchTree.add(1);
		binarySearchTree.add(5);
		binarySearchTree.add(2);
		binarySearchTree.add(4);
		binarySearchTree.add(1);
		binarySearchTree.add(7);
		binarySearchTree.add(9);
		binarySearchTree.add(8);
		binarySearchTree.add(3);
		binarySearchTree.add(6);
		Iterable<Integer> integers = binarySearchTree.elements();
		integers.forEach(System.out::println);
	}

}
