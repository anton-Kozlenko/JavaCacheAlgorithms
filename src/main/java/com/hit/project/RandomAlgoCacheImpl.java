package com.hit.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomAlgoCacheImpl<K, V> extends AbstractAlgoCache<K, V>{
	
	Map<K,V> myRandomHash;
	ArrayList<K> storeKeys;
	short keysTotal;

	public RandomAlgoCacheImpl(int capacity) {
		super(capacity);
		keysTotal = 0;
		myRandomHash = new HashMap<>();
		storeKeys = new ArrayList<K>();
	}

	@Override
	public V getElement(K key) {
		
		if(myRandomHash.containsKey(key)) {
			V node =  myRandomHash.get(key);
			return node;
		}
		return null;
	}

	@Override
	public V putElement(K key, V value) {
		
		V replacedValue = null;
		if(keysTotal == this.capacity) {
			int randomIndex = getRandomIndex();
			K randomKey = storeKeys.get(randomIndex);
			replacedValue = myRandomHash.get(randomIndex);
			myRandomHash.remove(randomKey);
		}else {
			keysTotal++;
		}
		storeKeys.add(key);
		myRandomHash.put(key, value);
		return replacedValue;
	}

	@Override
	public void removeElement(K key) {
		
		if(myRandomHash.containsKey(key)) {
			storeKeys.remove(key);
			myRandomHash.remove(key);
			keysTotal--;
		}
	}
	
	private int getRandomIndex() {
		Random rand = new Random();
		int randomKey = rand.nextInt(this.storeKeys.size() - 1);
		return randomKey;
	}
	
	@Override
	public List<K> getKeysList() {
		return  new ArrayList<K>(this.myRandomHash.keySet());
	}

}
