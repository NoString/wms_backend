package com.zhs.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.system.annotation.ManagerAuth;
import com.zhs.system.config.BaseController;
import com.zhs.system.entity.Users;
import com.zhs.system.service.UsersService;
import com.zhs.system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController extends BaseController {

    @Autowired
    private UsersService usersService;


    @GetMapping("/list")
    public R list(@RequestParam(required = false) Map<String, Object> params,
                  @RequestParam(required = false) ArrayList<String> columns) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        if (params.get("all") != null) {
            allLike(wrapper, (String) params.get("all"), columns,params);
        }
        parseParamToWrapper(params, wrapper, Users.class);
        List<Users> list = usersService.list(wrapper);
        return R.ok(list);
    }
    @ManagerAuth("delete user")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Users> usersList) {
        usersService.removeBatchByIds(usersList);
        return R.ok("delete success");
    }

    @ManagerAuth("add user")
    @PostMapping("/add")
    public R add(@RequestBody List<Users> usersList){
        usersService.saveBatch(usersList);
        return R.ok("add success");
    }
}
