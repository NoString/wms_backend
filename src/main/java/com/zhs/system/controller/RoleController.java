package com.zhs.system.controller;

import com.zhs.system.config.BaseController;
import com.zhs.system.service.RoleService;
import com.zhs.system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/query")
    public R roleQuery(){
        List<Map<String, Object>> result = roleService.listMaps();
//        HashMap<String, Object> defaultMap = new HashMap<>();
//        defaultMap.put("value","");
//        defaultMap.put("label","");

        for (Map<String, Object> map : result) {
            map.put("value",map.get("id"));
            map.put("label",map.get("name"));
        }
        return R.ok(result);
    }
}
