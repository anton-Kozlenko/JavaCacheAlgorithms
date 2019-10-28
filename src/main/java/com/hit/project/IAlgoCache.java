package com.hit.project;

import java.util.List;

public interface IAlgoCache<K,V> {
	
	V getElement(K key);
	V putElement(K key, V value);
	void removeElement(K key);
	public List<K> getKeysList();
}
