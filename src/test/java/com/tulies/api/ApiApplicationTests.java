package com.tulies.api;

import com.tulies.api.beans.form.UserUpdateForm;
import com.tulies.api.entity.User;
import com.tulies.api.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

//@SpringBootTest
@Slf4j
class ApiApplicationTests {

    @Test
    void contextLoads() {
        UserUpdateForm userUpdateForm = new UserUpdateForm();
        userUpdateForm.setAlias("hahah");
        userUpdateForm.setUid("12233");

        User user = new User();
        user.setId(1);
        user.setAlias("王嘉炀");
        user.setStatus(1);
        user.setUsername("tulies");
//
        BeanUtil.copyProperties(userUpdateForm, user);
        log.info("user={}",user);

//        log.info("{}",CommUtil.getNullPropertyNames(userUpdateForm));

    }
    @Test
    void settest() {
        Set<String> set1 = new HashSet<String>();
        set1.add("hahah1");
        set1.add("hahah3");
        Set<String> set2 = new HashSet<String>();
        set2.add("hahah1");
        set2.add("hahah2");
        set2.add("hahah3");
        set2.add("hahah4");
//        Set<String> set = new HashSet<String>();
//        set.addAll(set2);
        set2.addAll(set1);

        System.out.println(set2);
    }



}
