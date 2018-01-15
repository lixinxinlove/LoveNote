package com.love.lixinxin.lovenote;

import com.google.gson.Gson;
import com.love.lixinxin.lovenote.data.entity.User;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void gsonToStr() throws Exception {


        Gson gson=new Gson();

        User user=new User();

        user.setName(true);
        user.setSex(false);
        user.setType("man");

        boolean li= (boolean) user.getName();

        String jsonStr= gson.toJson(user);
        System.out.print(jsonStr);
        System.out.print(li);
    }

}