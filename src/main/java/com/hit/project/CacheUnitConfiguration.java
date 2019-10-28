package com.hit.project;

import java.io.Serializable;

public class CacheUnitConfiguration implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5804526762355721358L;
	private int capacity;
	private String algoName;
	private static CacheUnitConfiguration singleConfig = new CacheUnitConfiguration();
	
	private CacheUnitConfiguration() {}
	
	public static CacheUnitConfiguration getConfigInstance() {
		return singleConfig;
	}
	
	public static void setCapacity(int newCapacity) {
		singleConfig.capacity = newCapacity;
	}
	
	public static void setAlgorithm(String newAlgoName) {
		singleConfig.algoName = newAlgoName;
	}
	
}
