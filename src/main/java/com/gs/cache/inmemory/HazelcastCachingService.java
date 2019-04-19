package com.gs.cache.inmemory;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PACKAGE,makeFinal= true)
public class HazelcastCachingService implements IHazelcastCaching {

	@NonNull
	HazelcastInstance hz;
	
	@Override
	public <K, V> IMap<K, V> getMap(String id) {
		final IMap<K, V> map = hz.getMap(id);
		return map;
	}

	@Override
	public <T> IQueue<T> getQueue(String id) {
		final IQueue<T> queue = hz.getQueue(id);
		return queue;
	}


}
