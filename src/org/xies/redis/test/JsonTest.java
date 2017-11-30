package org.xies.redis.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.xies.redis.entity.User;
import org.xies.redis.util.FastJsonConvert;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author xies
 * @date 2017/11/29
 */
public class JsonTest {
    @Test
    public void testJson(){
        User user = new User(UUID.randomUUID().toString(),"xies",21,"13597609138","湖北省黄冈市");
        String text = FastJsonConvert.convertObjectToJSON(user);
        System.out.println(text);
        User user2 = new User(UUID.randomUUID().toString(),"jack",22,"15982608754","湖北武汉");
        List<User> list=new ArrayList<>();
        list.add(user);
        list.add(user2);
        String bigData = FastJsonConvert.convertObjectToJSON(list);
        System.out.println("bigData: "+bigData);
        List<User> list2 = FastJsonConvert.convertJSONToArray(bigData,User.class);
        System.out.println(list2.get(0).getId());
        System.out.println(list2.get(1).getId());
    }
}
