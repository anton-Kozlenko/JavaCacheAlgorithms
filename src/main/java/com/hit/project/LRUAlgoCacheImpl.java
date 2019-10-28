package com.hit.project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LRUAlgoCacheImpl<K,V> extends AbstractAlgoCache<K,V>{

	Map<K,V> myLRUhash;
	short keysTotal;
	
	public LRUAlgoCacheImpl(int capacity) {
		
		super(capacity);
		keysTotal = 0;
		myLRUhash = new LinkedHashMap<>(capacity, 0.75f, true);
	}

	@Override
	public V getElement(K key) {
		
		if(myLRUhash.containsKey(key)) {
			V node =  myLRUhash.get(key);
			return node;
		}
		return null;
	}

	@Override
	public V putElement(K key, V value) {
		
		V replacedValue = null;
		if(keysTotal == this.capacity) {
			K leastRecentlyUsedKey = myLRUhash.keySet().iterator().next();
			replacedValue = myLRUhash.get(leastRecentlyUsedKey);
			myLRUhash.remove(leastRecentlyUsedKey);
		}else {
			keysTotal++;
		}
		myLRUhash.put(key, value);
		return replacedValue;
	}

	@Override
	public void removeElement(K key) {
		
		if(myLRUhash.containsKey(key)) {
			myLRUhash.remove(key);
			keysTotal--;
		}
	}
	
	@Override
	public List<K> getKeysList() {
		return  new ArrayList<K>(this.myLRUhash.keySet());
	}

}
