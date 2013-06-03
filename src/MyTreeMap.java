import java.util.HashSet;
import java.util.Set;

import tree.BinaryTree;

public class MyTreeMap<K extends Comparable<K>, V> {
	BinaryTree<Entry<K, V>>	bt;
	int						size	= 0;
	
	public MyTreeMap() {
		bt = new BinaryTree<Entry<K, V>>(null);
	}
	
	public MyTreeMap(Entry<K, V> root) {
		bt = new BinaryTree<Entry<K, V>>(root);
		size++;
	}
	
	public V containsKey(Entry<K, V> element, BinaryTree<Entry<K, V>> tree) {
		if (tree == null)
			return null;
		else {
			Entry<K, V> r = tree.getRoot();
			if (r != null) {
				if (r.getKey().equals(element.getKey()))
					return r.getValue();
				else if (element.getKey().compareTo(r.getKey()) < 0)
					return containsKey(element, tree.getLeft());
				else if (element.getKey().compareTo(r.getKey()) > 0)
					return containsKey(element, tree.getRight());
			} else
				return null;
		}
		return null;
	}
	
	public V containsKey(Entry<K, V> element) {
		if (bt == null)
			return null;
		else
			return containsKey(element, bt);
	}
	
	public V get(Entry<K, V> element) {
		return this.containsKey(element);
	}
	
	public V put(Entry<K, V> element, BinaryTree<Entry<K, V>> tree) {
		Entry<K, V> r = tree.getRoot();
		if (r != null) {
			if (r.getKey().compareTo(element.getKey()) == 0) {
				V tempval = r.getValue();
				r.setValue(element.getValue());
				return tempval;
			} else if (element.getKey().compareTo(r.getKey()) < 0) {
				if (tree.getLeft() == null) {
					tree.setLeft(new BinaryTree<Entry<K, V>>(element));
					size++;
				} else
					return put(element, tree.getLeft());
			} else if (element.getKey().compareTo(r.getKey()) > 0) {
				if (tree.getRight() == null) {
					tree.setRight(new BinaryTree<Entry<K, V>>(element));
					size++;
				} else
					return put(element, tree.getRight());
			}
		} else
			tree.setRoot(element);
		return null;
	}
	
	public V put(Entry<K, V> element) {
		if (bt == null)
			bt = new BinaryTree<Entry<K, V>>(element);
		else
			return put(element, bt);
		return null;
	}
	
	public V remove(Entry<K, V> element) {
		return remove(element, bt);
	}
	
	public V remove(Entry<K, V> element, BinaryTree<Entry<K, V>> tree) {
		if (tree != null) {
			Entry<K, V> r = tree.getRoot();
			if (element.getKey().compareTo(r.getKey()) < 0) {
				return remove(element, tree.getLeft());
			} else if (element.getKey().compareTo(r.getKey()) > 0) {
				return remove(element, tree.getRight());
			} else if (element.getKey().compareTo(r.getKey()) == 0) {
				if (tree.isLeaf()) {
					tree.setRoot(null);
					tree = null;
				} else if (tree.getRight() == null && tree.getLeft() != null) {
					tree.setRoot(tree.getLeft().getRoot());
					tree = tree.getLeft();
				} else if (tree.getRight() != null && tree.getLeft() == null) {
					tree.setRoot(tree.getRight().getRoot());
					tree = tree.getRight();
				} else {
					tree.setRoot(this.successor(tree).getRoot());
					tree = this.successor(tree);
				}
				size--;
				return r.getValue();
			}
		}
		return null;
	}
	
	private BinaryTree<Entry<K, V>> successor(BinaryTree<Entry<K, V>> tree) {
		BinaryTree<Entry<K, V>> parent = tree;
		BinaryTree<Entry<K, V>> cursor = tree.getRight();
		while (cursor.getLeft() != null) {
			parent = cursor;
			cursor = cursor.getLeft();
		}
		return parent;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		String s = "";
		for (K it : keySet())
			s += (it + ": " + this.containsKey(new Entry<K, V>(it, null)) + "\n");
		return s;
	}
	
	public Set<K> keySet() {
		Set<K> keys = new HashSet<K>();
		return getKeys(bt, keys);
	}
	
	public Set<K> getKeys(BinaryTree<Entry<K, V>> tree, Set<K> keys) {
		if (tree.getRoot() != null) {
			keys.add(tree.getRoot().getKey());
			if (tree.getRight() != null)
				this.getKeys(tree.getRight(), keys);
			if (tree.getLeft() != null)
				this.getKeys(tree.getLeft(), keys);
		}
		return keys;
	}
}
