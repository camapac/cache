package com.gs.cache.redis;

public interface IRedisCaching {

	void addToHash(String key,String hkey, Object hvalue);
	void addToHash(String key,String hkey, Object hvalue,long expiredInSecond);
	void removeFromHash(String key,String hkey);
	Object getFromHash(String key, String hkey);
    void addOpsValue(String key, Object value);
	void addOpsValue(String key, Object value, long expiredInSecond);
	boolean removeFromOpsValue(String key);
	Object getFromOpsValue(String key);
	
}
