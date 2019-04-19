package com.gs.cache.cacheutils;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gs.cache.annotation.EnableInMemoryCaching;
import com.gs.cache.inmemory.HazelcastCachingService;
import com.hazelcast.core.IMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableInMemoryCaching
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InmemoryCachingTest {

	@Autowired
	private HazelcastCachingService hazelcastCachingService;

	private IMap<String, String> cachedMap = null;

	@Before
	public void setUp() {
		if (cachedMap == null)
			cachedMap = hazelcastCachingService.getMap("my_cached");
		
		cachedMap.put("key", "value");

	}

	@Test
	public void test1addValue() {
		cachedMap.put("key1", "value1");
	}
	
	
	@Test
	public void test2getValue() {
		assertTrue(cachedMap.get("key").equals("value"));
		assertTrue(cachedMap.get("key1").equals("value1"));
	}
	
	@Test
	public void test3size() {
		assertTrue(cachedMap.size()==2);
	}

}
