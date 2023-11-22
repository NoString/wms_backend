package com.zhs.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.common.entity.WmsLocHead;
import com.zhs.common.entity.WmsMaterial;
import com.zhs.common.service.IWmsLocHeadService;
import com.zhs.system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
 * @since 2023-11-22
 */
@RestController
@RequestMapping("/common/wmsLocHead")
public class WmsLocHeadController extends BaseController {

    @Autowired
    private IWmsLocHeadService locHeadService;

    @GetMapping("/list")
    public R list(@RequestParam(required = false) Map<String, Object> params,
                  @RequestParam(required = false) ArrayList<String> columns) {
        QueryWrapper<WmsLocHead> wrapper = new QueryWrapper<>();
        if (params.get("all") != null) {
            allLike(wrapper, (String) params.get("all"), columns,params, WmsLocHead.class);
        }
        parseParamToWrapper(params, wrapper);
        List<WmsLocHead> list = locHeadService.list(wrapper);
        return R.ok(list);
    }

    @RequestMapping("/init")
    public R init(){
        locHeadService.remove(null);
        ArrayList<WmsLocHead> wmsLocHeads = new ArrayList<>();
        //row
        for (int i = 1; i < 11; i++) {
            //bay
            for (int j = 1; j < 31; j++) {
                //level
                for (int k = 1; k < 11; k++) {
                    String row = String.format("%02d", i);
                    String bay = String.format("%02d", j);
                    String level = String.format("%02d", k);
                    String locNo = row + bay + level;
                    WmsLocHead wmsLocHead = new WmsLocHead(locNo,row,bay,level,"E.Empty Storage","H.High Storage");
                    wmsLocHeads.add(wmsLocHead);
                }
            }
        }
        locHeadService.saveBatch(wmsLocHeads);
        return R.ok();
    }
}
