package com.gs.cache.inmemory;

import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;

public interface IHazelcastCaching {

	public <K,V> IMap<K, V> getMap(final String id);
	
	public <T> IQueue<T> getQueue(final String id);
	
}
