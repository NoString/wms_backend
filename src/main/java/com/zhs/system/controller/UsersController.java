package com.zhs.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.system.annotation.ManagerAuth;
import com.zhs.system.config.BaseController;
import com.zhs.system.entity.Users;
import com.zhs.system.service.UsersService;
import com.zhs.system.utils.Check;
import com.zhs.system.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.zhs.system.utils.Constant.DENIED;

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
            allLike(wrapper, (String) params.get("all"), columns,params, Users.class);
        }
        parseParamToWrapper(params, wrapper);
        List<Users> list = usersService.list(wrapper);
        return R.ok(list);
    }
    @ManagerAuth("delete user")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Users> usersList) {
        usersService.removeBatchByIds(usersList);
        return R.ok("Successfully delete");
    }

    @ManagerAuth("add user")
    @PostMapping("/add")
    public R add(HttpServletRequest request,
                  @RequestBody List<Users> usersList){
        Date date = new Date();
        Long id = Long.valueOf(request.getHeader("id"));
        boolean userExist = false;

        if (Check.isEmpty(id)) {
            return R.parse(DENIED);
        }

        for (Users user : usersList) {
            if (usersService.getOne(new QueryWrapper<Users>()
                    .eq("username", user.getUsername())) != null) {
                userExist = true;
                break;
            }
            user.setCreateTime(date);
            user.setUpdateTime(date);
            user.setCreateBy(id);
            user.setUpdateBy(id);
        }
        if (userExist) {
            return R.error("The username is exist");
        }

        usersService.saveBatch(usersList);
        return R.ok("Successfully add. But you also need to reset password.");
    }

    @ManagerAuth("edit user")
    @PostMapping("/edit")
    public R edit(HttpServletRequest request,
                  @Validated @RequestBody Users user){
        usersService.updateById(user);
        return R.ok("Successfully edit");
    }

    @RequestMapping("/status/query")
    public R queryStatus(){
        List<Map<String, Object>> result = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("value",true);
        map1.put("label","available");
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("value",false);
        map2.put("label","unavailable");
        result.add(map1);
        result.add(map2);
        return R.ok(result);
    }
}
