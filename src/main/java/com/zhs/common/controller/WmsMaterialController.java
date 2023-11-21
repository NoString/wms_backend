package com.zhs.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zhs.common.entity.WmsMaterial;
import com.zhs.common.service.IWmsMaterialService;
import com.zhs.system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zhs.system.config.BaseController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZHU HANGSHUAI
 * @since 2023-11-20
 */
@RestController
@RequestMapping("/common/wmsMaterial")
public class WmsMaterialController extends BaseController {
    @Autowired
    private IWmsMaterialService materialService;

    @GetMapping("/list")
    public R list(@RequestParam(required = false) Map<String, Object> params,
                  @RequestParam(required = false) ArrayList<String> columns) {
        QueryWrapper<WmsMaterial> wrapper = new QueryWrapper<>();
        if (params.get("all") != null) {
            allLike(wrapper, (String) params.get("all"), columns,params, WmsMaterial.class);
        }
        parseParamToWrapper(params, wrapper);
        List<WmsMaterial> list = materialService.list(wrapper);
        return R.ok(list);
    }
}
