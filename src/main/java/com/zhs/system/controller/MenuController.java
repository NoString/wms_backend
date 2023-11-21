package com.zhs.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.system.config.BaseController;
import com.zhs.system.entity.Menu;
import com.zhs.system.service.MenuService;
import com.zhs.system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
