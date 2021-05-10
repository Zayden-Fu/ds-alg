package org.foo.ds.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * User: fuzongyang
 * Date: 2021/5/2
 * Time: 11:10 PM
 */
public class BinarySearchTree<E extends Comparable<E>> {

	private Node root;

	public BinarySearchTree() {
	}

	/**
	 * 新增
	 */
	public void add(E e) {
		checkIfNull(e);
		root = add(root, e);
	}

	/**
	 * 删除
	 */
	public void delete(E e) {
		checkIfNull(e);
		root = delete(root, e);
	}

	/**
	 * 是否是空树
	 */
	public Boolean isEmpty() {
		return size(root) == 0;
	}

	/**
	 * 树的大小
	 */
	public int size() {
		return size(root);
	}

	/**
	 * 是否包含E
	 */
	public boolean contains(E e) {
		checkIfNull(e);
		return get(root, e) != null;
	}

	public Iterable<E> elements() {
		// 采用中序遍历
		Queue<E> queue = new ArrayDeque<>();
		addQueueOfInOrder(root, queue);
		return queue;
	}

	private void addQueueOfInOrder(Node node, Queue<E> queue) {
		if (node != null) {
			addQueueOfInOrder(node.left, queue);
			queue.offer(node.element);
			addQueueOfInOrder(node.right, queue);
		}
	}


	private E get(Node node, E e) {
		if (node == null) {
			return null;
		}
		int cmp = e.compareTo(node.element);
		if (cmp < 0) {
			return get(node.left, e);
		} else if (cmp > 0) {
			return get(node.right, e);
		} else {
			return e;
		}
	}


	private Node delete(Node node, E e) {
		if (node == null) {
			return null;
		}
		int cmp = e.compareTo(node.element);
		if (cmp < 0) {
			node.left = delete(node.left, e);
		} else if (cmp > 0) {
			node.right = delete(node.right, e);
		} else {
			// 无子节点或者单个子节点的情况
			if (node.right == null || node.left == null) {
				return node.right == null ? node.left : node.right;
			}
			// 双子节点，找到右边最小的节点进行替换
			Node temp = node;
			// 找到最小的节点
			node = min(temp.right);
			// 最小节点的右边等于删除最小节点后的树
			node.right = deleteMin(temp.right);
			node.left = temp.left;
		}
		node.size = 1 + size(node.left) + size(node.right);
		return node;

	}

	private Node deleteMin(Node node) {
		// 本身就是最小的
		if (node.left == null) {
			return node.right;
		}
		node.left = deleteMin(node.left);
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}

	private Node min(Node node) {
		// 本身就是最小的
		if (node.left == null) {
			return node;
		}
		return min(node.left);
	}

	private Node add(Node node, E e) {
		if (node == null) {
			return new Node(e, 1);
		}
		int cmp = e.compareTo(node.element);
		if (cmp < 0) {
			node.left = add(node.left, e);
		} else if (cmp > 0) {
			node.right = add(node.right, e);
		}
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}

	private int size(Node node) {
		if (node == null) {
			return 0;
		}
		return node.size;
	}

	private void checkIfNull(E e) {
		if (e == null) {
			throw new IllegalArgumentException("The element is null!");
		}
	}

	private class Node {

		private E element;
		private Node left, right;
		// node count
		private int size;

		public Node(E element, int size) {
			this.element = element;
			this.size = size;
		}

	}

}
