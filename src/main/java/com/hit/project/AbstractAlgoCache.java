package com.hit.project;


public abstract class AbstractAlgoCache<K,V> implements IAlgoCache<K,V>{

	int capacity;
	
	public AbstractAlgoCache(int capacity) {
		this.capacity = capacity;
	}
	
}
