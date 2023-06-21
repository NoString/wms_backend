package com.zhs.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.system.annotation.ManagerAuth;
import com.zhs.system.config.BaseController;
import com.zhs.system.entity.Menu;
import com.zhs.system.entity.Users;
import com.zhs.system.service.MenuService;
import com.zhs.system.utils.Check;
import com.zhs.system.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.zhs.system.utils.Constant.DENIED;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;


    @GetMapping("/list")
    public R list(@RequestParam(required = false) Map<String, Object> params,
                  @RequestParam(required = false) ArrayList<String> columns) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        parseParamToWrapper(params, wrapper);
        List<Menu> list = menuService.list(wrapper);
        return R.ok(list);
    }

}
