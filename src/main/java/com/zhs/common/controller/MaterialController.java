package com.zhs.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.common.entity.Classify;
import com.zhs.common.entity.Material;
import com.zhs.common.service.MaterialService;
import com.zhs.system.annotation.ManagerAuth;
import com.zhs.system.config.BaseController;
import com.zhs.system.utils.Check;
import com.zhs.system.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.zhs.system.utils.Constant.DENIED;

@RestController
@RequestMapping("/material")
public class MaterialController extends BaseController {

    @Autowired
    private MaterialService materialService;


    @GetMapping("/list")
    public R list(@RequestParam(required = false) Map<String, Object> params) {
        QueryWrapper<Material> wrapper = new QueryWrapper<>();
        parseParamToWrapper(params, wrapper);
        List<Material> list = materialService.list(wrapper);
        return R.ok(list);
    }
    @ManagerAuth("delete material")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Material> usersList) {
        materialService.removeBatchByIds(usersList);
        return R.ok("Successfully delete");
    }

    @ManagerAuth("add material")
    @PostMapping("/add")
    public R add(HttpServletRequest request,
                  @RequestBody List<Material> list){
        Date date = new Date();
        Long id = Long.valueOf(request.getHeader("id"));

        if (Check.isEmpty(id)) {
            return R.parse(DENIED);
        }

        for (Material user : list) {
            user.setCreateTime(date);
            user.setUpdateTime(date);
            user.setCreateBy(id);
            user.setUpdateBy(id);
        }


        materialService.saveBatch(list);
        return R.ok("Successfully add. But you also need to reset password.");
    }

    @ManagerAuth("edit material")
    @PostMapping("/edit")
    public R edit(HttpServletRequest request,
                  @RequestBody Material material){
        Date date = new Date();
        Long id = Long.valueOf(request.getHeader("id"));

        if (Check.isEmpty(id)) {
            return R.parse(DENIED);
        }
        material.setUpdateBy(id);
        material.setUpdateTime(date);
        materialService.updateById(material);
        return R.ok("Successfully edit");
    }

}
