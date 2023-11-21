package com.zhs.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.common.entity.Classify;
import com.zhs.common.service.ClassifyService;
import com.zhs.system.annotation.ManagerAuth;
import com.zhs.system.config.BaseController;
import com.zhs.system.utils.Check;
import com.zhs.system.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.zhs.system.utils.Constant.DENIED;

@RestController
@RequestMapping("/classify")
public class ClassifyController extends BaseController {

    @Autowired
    private ClassifyService classifyService;


    @GetMapping("/list")
    public R list(@RequestParam(required = false) Map<String, Object> params) {
        QueryWrapper<Classify> wrapper = new QueryWrapper<>();
        parseParamToWrapper(params, wrapper);
        List<Classify> list = classifyService.list(wrapper);
        return R.ok(list);
    }
    @ManagerAuth("delete classify")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Classify> usersList) {
        classifyService.removeBatchByIds(usersList);
        return R.ok("Successfully delete");
    }

    @ManagerAuth("add classify")
    @PostMapping("/add")
    public R add(HttpServletRequest request,
                  @RequestBody List<Classify> list){
        Date date = new Date();
        Long id = Long.valueOf(request.getHeader("id"));

        if (Check.isEmpty(id)) {
            return R.parse(DENIED);
        }

        for (Classify item : list) {
            item.setCreateTime(date);
            item.setUpdateTime(date);
            item.setCreateBy(id);
            item.setUpdateBy(id);
        }


        classifyService.saveBatch(list);
        return R.ok("Successfully add.");
    }

    @ManagerAuth("edit classify")
    @PostMapping("/edit")
    public R edit(HttpServletRequest request,
                  @RequestBody Classify classify){
        Date date = new Date();
        Long id = Long.valueOf(request.getHeader("id"));

        if (Check.isEmpty(id)) {
            return R.parse(DENIED);
        }
        classify.setUpdateBy(id);
        classify.setUpdateTime(date);
        classifyService.updateById(classify);
        return R.ok("Successfully edit");
    }

}
