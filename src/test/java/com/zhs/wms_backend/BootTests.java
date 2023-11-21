package com.zhs.wms_backend;

import com.zhs.system.entity.User;
import com.zhs.system.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class BootTests {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    void contextLoads() {
        List<User> users = usersMapper.selectList(null);

    }

}
