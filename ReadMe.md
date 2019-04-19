# This is project library for using caching data 


# How to use
The project using @Conditional to control spring bean configuration.
The first you should add the configurations below to the configuration file.

<code>
cache.inmemory.enable=true //enable inmemory caching</br> 
cache.redis.enable=true //enable redis caching
</code>

<p> If you using Redis for caching solutions you must set the configuration.
 In this case I using the Redis on single node </p>
<code>
cache.redis.hostname= </br> 
cache.redis.port= </br> 
cache.redis.password=
</code>


<p>The second you must enable the configuration by using anotation configuration </p>
<code>
@EnableInMemoryCaching //for InMemory </br> 
@EnableRedisCaching //for Redis
</code>


<p> Here is example for Redis testing <p>
<code>
@RunWith(SpringRunner.class)</br> 
@SpringBootTest(classes= {RedisCachingService.class})</br> 
@EnableRedisCaching</br> 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)</br> 
public class RedisCachingTesting {</br> 
	
	@Autowired
	private RedisCachingService redisService;
	
	@Test
	public void test1addValue() {
		redisService.addOpsValue("key", "value");
	}
	
	@Test
	public void test2GetValue() {
		assertTrue(redisService.getFromOpsValue("key").equals("value"));
	}
}
</code>


<p>
For future implementation and maintain you can apply the cluster caching for Redis or distributed caching with Hazelcast
</p>

### Reference Documentation
For further reference, please consider the following sections:

* [Hazelcast Caching](https://hazelcast.org/)

* [Spring Data Redis](https://docs.spring.io/spring-data/redis/docs/2.1.6.RELEASE/reference/html/)