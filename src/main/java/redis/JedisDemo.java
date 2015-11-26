package redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 *  redis内存数据库
  * @author angilin
  *
 */
public class JedisDemo {
	

	public static void main(String[] args)  throws Exception{         
	    
		JedisDemo jedisDemo = new JedisDemo();
		jedisDemo.test();
	    
	}
	
	/**
	 * Jedis 连接池JedisPool 解决connection timeout问题
	  * http://java-er.com/blog/jedispool/
	 */
	public void test(){
		JedisPoolConfig config = new JedisPoolConfig();
	    
		config.setMaxActive(100);
 
		config.setMaxIdle(20);
 
		config.setMaxWait(1000l);
		//需要JedisPool依赖apache类包commons-pool-1.5.6.jar
		JedisPool pool = new JedisPool(config, "localhost",6379 );
		Jedis  jedis = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = pool.getResource();
			//jedis.auth("redis密码");
			jedis.set("aa", "1000");
			Map map = new HashMap(); 
		    map.put("id", "1000"); 
		    map.put("username", "aaa"); 
		    jedis.hmset("user", map); 
			
			System.out.println(jedis.get("aa"));
			System.out.println(jedis.hget("user", "username"));
				
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				pool.returnBrokenResource(jedis);
 
		} finally {
			if (borrowOrOprSuccess)
				pool.returnResource(jedis);
		}
	    
	}
	
	
	//范例
	public void testDeom(){  
	    Jedis  redis = new Jedis("localhost",6379);//连接redis

	    //hset key field value将哈希表key中的域field的值设为value。 
	    redis.hset("yyweb", "music", "m.yy.com"); 
	    redis.hset("yyweb", "mall", "mai.yy.com"); 
	    redis.hset("yyweb", "duowan", "www.duowan.com"); 
	    //返回哈希表key中，一个或多个给定域的值。 
	    List list = redis.hmget("yyweb","music","mall","duowan"); 
	    for(int i=0;i<list.size();i++){ 
	        System.out.println(list.get(i)); 
	    }

	    //同时将多个field - value(域-值)对设置到哈希表key中。 
	    Map map = new HashMap(); 
	    map.put("uid", "10000"); 
	    map.put("username", "chenxu"); 
	    redis.hmset("hash", map); 
	    //得到map下面的username的值 
	    System.out.println(redis.hget("hash", "username"));

	    //HGETALL key返回哈希表key中，所有的域和值。 
	    Map<String,String> maps = redis.hgetAll("hash"); 
	    for(Map.Entry entry: maps.entrySet()) { 
	         System.out.print(entry.getKey() + ":" + entry.getValue() + "\t"); 
	    }
	}

}
