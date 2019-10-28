package com.hit.project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IAlgoCacheTest {

	@Test
	public void test() {
		
		List<Integer> desiredKeys = new ArrayList<Integer>();
		desiredKeys.add(2);
		desiredKeys.add(4);
		desiredKeys.add(6);
		desiredKeys.add(7);
		desiredKeys.add(8);
		
		IAlgoCache<Integer, String> myLRU = new SecondChance<Integer, String>(5);
		myLRU.putElement(Integer.valueOf(1), "anton");
		myLRU.putElement(Integer.valueOf(2), "elina");
		myLRU.putElement(Integer.valueOf(3), "artyem");
		myLRU.putElement(Integer.valueOf(4), "katya");
		myLRU.getElement(1);
		myLRU.getElement(4);
		myLRU.putElement(Integer.valueOf(5), "anuta");
		myLRU.getElement(2);
		myLRU.putElement(Integer.valueOf(6), "stas");
		myLRU.getElement(2);
		myLRU.putElement(Integer.valueOf(7), "stas");
		myLRU.putElement(Integer.valueOf(8), "stas");
		
		assertEquals(desiredKeys, myLRU.getKeysList());
	}

}
