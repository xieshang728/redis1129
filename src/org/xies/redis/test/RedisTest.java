package org.xies.redis.test;

import com.sun.prism.shader.DrawPgram_ImagePattern_Loader;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xies
 * @date 2017/11/29
 */
public class RedisTest {

    private static Jedis jedis = new Jedis("47.94.234.154",6379);

    @Test
    public void test(){
        testPipelined();
    }

    /**
     * 测试String
     */
    public static void testStr(){
        jedis.set("name","xieshang");
        System.out.println("name: "+jedis.get("name"));

        jedis.append("name"," is my name");
        System.out.println("name: "+jedis.get("name"));

        jedis.del("name");
        System.out.println("name: "+jedis.get("name"));

        jedis.mset("name","xies","age","21","qq","1260409282");
        jedis.incr("age");
        System.out.println(jedis.get("name")+"-"+jedis.get("age")+"-"+jedis.get("qq"));

    }

    /**
     * 测试List
     */
    public static void testList(){
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework",0,-1));

        jedis.lpush("java framework","spring");
        jedis.lpush("java framework","spring mvc");
        jedis.lpush("java framework","mybatis");
        System.out.println(jedis.lrange("java framework",0,-1));

        jedis.del("java framework");
        jedis.rpush("java framework","spring");
        jedis.rpush("java framework","spring mvc");
        jedis.rpush("java framework","mybatis");
        System.out.println(jedis.lrange("java framework",0,-1));
    }

    /**
     * 测试set
     */
    public static void testSet(){
        jedis.sadd("user1","mark");
        jedis.sadd("user1","jack");
        jedis.sadd("user1","mark");
        jedis.sadd("user1","rose");
        jedis.sadd("user1","jimi");

        System.out.println(jedis.smembers("user1"));
        System.out.println(jedis.sismember("user1","mark"));
        System.out.println(jedis.srandmember("user1"));
        System.out.println(jedis.scard("user1"));
    }

    /**
     * 测试map
     */
    public static void testMap(){
        Map<String,String> user = new HashMap<>();
        user.put("name","xies");
        user.put("age","21");
        user.put("address","湖北黄冈");

        jedis.hmset("xies",user);
        List<String> list = jedis.hmget("xies","name","age","address");
        for(String element:list){
            System.out.println(element);
        }
        System.out.println(jedis.hkeys("xies"));
        System.out.println(jedis.hvals("xies"));
    }

    /**
     * 测试zset
     */
    public static void testZset(){
        jedis.zadd("programming",1,"java");
        jedis.zadd("programming",2,"c++");
        jedis.zadd("programming",3,"python");
        System.out.println(jedis.zrange("programming",0,-1));
        System.out.println(jedis.zscore("programming","java"));

    }

    /**
     * 测试事务
     */
    public static void testTrans(){
        long start = System.currentTimeMillis();
        Transaction tx = jedis.multi();
        for(int i=0 ; i < 100 ; i++){
            tx.sadd("tx",i+"");
//            if(i == 50){
//                System.out.println(10/0);
//            }
        }
        List<Object> results = tx.exec();
        long end = System.currentTimeMillis();
        System.out.println("Transaction SET: "+(end-start)/1000.0+" seconds");
    }

    /**
     * 测试管道
     */
    public static void testPipelined(){
        Pipeline pipeline = jedis.pipelined();
        long start = System.currentTimeMillis();
        pipeline.multi();
        for(int i=0 ; i < 100 ; i++){
            pipeline.sadd("pipeline",i+"");
        }
        List<Object> result = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Transaction Pipelined: "+(end-start)/1000.0+" seconds");
    }
}
