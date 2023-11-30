package com.zhs.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.common.entity.WmsLocSts;
import com.zhs.common.entity.WmsMaterial;
import com.zhs.common.service.IWmsLocStsService;
import com.zhs.system.annotation.ManagerAuth;
import com.zhs.system.utils.Check;
import com.zhs.system.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zhs.system.config.BaseController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.zhs.system.utils.Constant.DENIED;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZHU HANGSHUAI
 * @since 2023-11-22
 */
@RestController
@RequestMapping("/common/wmsLocSts")
public class WmsLocStsController extends BaseController {

    @Autowired
    private IWmsLocStsService locStsService;

    @GetMapping("/list")
    public R list(@RequestParam(required = false) Map<String, Object> params,
                  @RequestParam(required = false) ArrayList<String> columns) {
        QueryWrapper<WmsLocSts> wrapper = new QueryWrapper<>();
        if (params.get("all") != null) {
            allLike(wrapper, (String) params.get("all"), columns,params, WmsLocSts.class);
        }
        parseParamToWrapper(params, wrapper);
        List<WmsLocSts> list = locStsService.list(wrapper);
        return R.ok(list);
    }


}
