package org.foo.ds.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * User: fuzongyang
 * Date: 2021/5/4
 * Time: 10:01 AM
 */
public class RedBlackTree<E extends Comparable<E>> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root;

	public RedBlackTree() {
	}

	public void add(E e) {
		checkIfNull(e);
		root = add(root, e);
		root.color = BLACK;
	}

	private Node add(Node node, E e) {
		if (node == null) {
			return new Node(e, 1, RED);
		}
		int cmp = e.compareTo(node.element);
		if (cmp < 0) {
			node.left = add(node.left, e);
		} else if (cmp > 0) {
			node.right = add(node.right, e);
		}

		// 按照顺序处理

		// 新key导致 right-link = red 且 left-link = black , 需要左旋
		if (isRed(node.right) && !isRed(node.left)) {
			node = rotateLeft(node);
		}
		// 左边连续两个red-link
		if (isRed(node.left) && isRed(node.left.left)) {
			node = rotateRight(node);
		}
		// 两个子node都是红色
		if (isRed(node.left) && isRed(node.right)) {
			flipColors(node);
		}
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}

	public void delete(E e) {
		checkIfNull(e);

		if (!contains(e)) {
			return;
		}

		// ??
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}

		root = delete(root, e);
		if (!isEmpty()) {
			root.color = BLACK;
		}

	}

	private boolean isEmpty() {
		return root == null;
	}

	private Node delete(Node node, E e) {

		if (e.compareTo(node.element) < 0) {
			if (!isRed(node.left) && !isRed(node.left.left)) {
				node = moveRedLeft(node);
			}
			node.left = delete(node.left, e);
		} else {
			if (isRed(node.left)) {
				node = rotateRight(node);
			}
			if (e.compareTo(node.element) == 0 && node.right == null) {
				return null;
			}
			if (!isRed(node.right) && !isRed(node.right.left)) {
				node = moveRedRight(node);
			}
			if (e.compareTo(node.element) == 0) {
				Node minNodeOfRight = min(node.right);
				node.element = minNodeOfRight.element;
				node.right = deleteMin(node.right);
			} else {
				node.right = delete(node.right, e);
			}
		}

		return balance(node);
	}

	public void deleteMin() {
		if (isEmpty()) {
			return;
		}
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}
		root = deleteMin(root);
		if (!isEmpty()) {
			root.color = BLACK;
		}
	}

	public void deleteMax() {
		if (isEmpty()) {
			return;
		}
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = RED;
		}
		root = deleteMax(root);
		if (!isEmpty()) {
			root.color = BLACK;
		}
	}

	private Node deleteMax(Node node) {
		if (isRed(node.left)) {
			node = rotateRight(node);
		}
		if(node.right == null){
			return null;
		}
		if(!isRed(node.right)&& !isRed(node.right.left)){
			node = moveRedRight(node);
		}
		node.right  = deleteMax(node.right);
		return balance(node);
	}

	private Node deleteMin(Node node) {
		if (node.left == null) {
			return null;
		}
		if (!isRed(node.left) && !isRed(node.left.left)) {
			node = moveRedLeft(node);
		}
		node.left = deleteMin(node.left);
		return balance(node);
	}

	private Node balance(Node node) {
		if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
		if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
		if (isRed(node.left) && isRed(node.right)) flipColors(node);
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}

	private Node min(Node node) {
		if (node.left == null) {
			return node;
		}
		return min(node.left);
	}

	private Node moveRedRight(Node node) {
		flipColors(node);
		if (isRed(node.left.left)) {
			node = rotateRight(node);
			flipColors(node);
		}
		return node;
	}

	private Node moveRedLeft(Node node) {
		flipColors(node);

		if (isRed(node.right.left)) {
			node.right = rotateRight(node.right);
			node = rotateLeft(node);
			flipColors(node);
		}
		return node;
	}

	public boolean contains(E e) {
		return get(root, e) != null;
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

	private void flipColors(Node node) {
		node.color = !node.color;
		node.left.color = !node.left.color;
		node.right.color = !node.right.color;
	}

	private Node rotateRight(Node node) {
		Node left = node.left;
		node.left = left.right;
		left.right = node;
		left.color = left.right.color;
		left.right.color = RED;
		left.size = node.size;
		node.size = size(node.left) + size(node.right) + 1;
		return left;
	}


	private void checkIfNull(E e) {
		if (e == null) {
			throw new IllegalArgumentException("The element is null!");
		}
	}

	private boolean isRed(Node x) {
		if (x == null) return false;
		return x.color == RED;
	}

	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = x.left.color;
		x.left.color = RED;
		x.size = h.size;
		h.size = 1 + size(h.left) + size(h.right);
		return x;
	}

	private int size(Node x) {
		if (x == null) return 0;
		return x.size;
	}

	private class Node {

		E element;
		Node left, right;
		int size;
		boolean color;

		public Node(E element, int size, boolean color) {
			this.element = element;
			this.size = size;
			this.color = color;
		}
	}

}
