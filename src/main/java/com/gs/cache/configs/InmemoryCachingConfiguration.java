package com.gs.cache.configs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gs.cache.inmemory.HazelcastCachingService;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.core.Hazelcast;

@Configuration
@ConditionalOnProperty(value="cache.inmemory.enable",havingValue="true")
public class InmemoryCachingConfiguration {
	
	
	@Bean
    public Config hazelCastConfig(){
        Config config = new Config();
        config.setInstanceName("hazelcast-instance")        
                .addMapConfig(
                        new MapConfig()                     
                                .setName("map_config")
                                .setMaxSizeConfig(new MaxSizeConfig(500, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(-1))
                .addQueueConfig(
                		new QueueConfig()
                				.setName("queue_config")
                				.setMaxSize(1000));
        return config;
    }
	
	
	@Bean
	public HazelcastCachingService hazelcastCachingService() {
		return new HazelcastCachingService(Hazelcast.getOrCreateHazelcastInstance(hazelCastConfig()));
	}

}
