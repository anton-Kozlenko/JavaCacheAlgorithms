package com.hit.project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SecondChance<K,V> extends AbstractAlgoCache<K,V> {

	LinkedHashMap<K, InnerReferenceBitNode<V>> myFifoHash;
	short keysTotal;
	
	public SecondChance(int capacity) {
		super(capacity);
		myFifoHash = new LinkedHashMap<>();
		keysTotal = 0;
	}

	@Override
	public V getElement(K key) {
		
		if(myFifoHash.containsKey(key)) {
			InnerReferenceBitNode<V> node =  myFifoHash.get(key);
			node.activateBit();
			return node.node;
		}
		return null;
	}

	@Override
	public V putElement(K key, V value) {
		
		InnerReferenceBitNode<V> replacedValue = null;
		
		if(keysTotal == this.capacity) {
			K firstOut = myFifoHash.keySet().iterator().next();
			K currentKey;
			
			for(Map.Entry<K, InnerReferenceBitNode<V>> pair : myFifoHash.entrySet()) {
				currentKey = pair.getKey();
				replacedValue = myFifoHash.get(currentKey);
				if(replacedValue.getBitStatus() != 0) {
					replacedValue.shutBit();
					continue;
				}
				firstOut = currentKey;
				break;
			}
			myFifoHash.remove(firstOut);
		}else {
			keysTotal += myFifoHash.containsKey(key) ? 0 : 1;
		}
		myFifoHash.put(key, new InnerReferenceBitNode<V>(value));
		return replacedValue == null ? null : replacedValue.node;
	}

	@Override
	public void removeElement(K key) {
		
		if(myFifoHash.containsKey(key)) {
			myFifoHash.remove(key);
			keysTotal--;
		}
	}
	
	@Override
	public List<K> getKeysList() {
		return  new ArrayList<K>(this.myFifoHash.keySet());
	}
	


	private class InnerReferenceBitNode<K>{
		short referenceBit;
		K node = null;
		
		public InnerReferenceBitNode(K node) {
			this.node = node;
			this.referenceBit = 0;
		}
		
		public void activateBit() {
			this.referenceBit = 1;
		}
		
		public void shutBit() {
			this.referenceBit = 0;
		}
		
		public short getBitStatus() {
			return this.referenceBit;
		}
	}


}





