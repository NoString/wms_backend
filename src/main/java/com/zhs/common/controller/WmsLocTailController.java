package com.zhs.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.common.entity.WmsLocHead;
import com.zhs.common.entity.WmsLocTail;
import com.zhs.common.service.IWmsLocTailService;
import com.zhs.system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zhs.system.config.BaseController;

import java.util.ArrayList;
import java.util.HashMap;
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
@RequestMapping("/common/wmsLocTail")
public class WmsLocTailController extends BaseController {
    @Autowired
    private IWmsLocTailService locTailService;

    @GetMapping("/list")
    public R list(@RequestParam(required = false) Map<String, Object> params,
                  @RequestParam(required = false) ArrayList<String> columns) {
        QueryWrapper<WmsLocTail> wrapper = new QueryWrapper<>();
        if (params.get("all") != null) {
            allLike(wrapper, (String) params.get("all"), columns,params, WmsLocTail.class);
        }
        parseParamToWrapper(params, wrapper);
        List<WmsLocTail> list = locTailService.list(wrapper);
        return R.ok(list);
    }

    @RequestMapping("/tailSts")
    public R getTailStsCount(){
        List<WmsLocTail> wmsLocTails = locTailService.list();
        HashMap<String, Integer> countStsMap = new HashMap<>();
        countStsMap.put("80",0);
        countStsMap.put("60",0);
        countStsMap.put("40",0);
        countStsMap.put("20",0);
        countStsMap.put("less20",0);

        for (WmsLocTail wmsLocTail : wmsLocTails) {
            if (wmsLocTail.getProgress() >= 80) {
                countStsMap.put("80",countStsMap.get("80") + 1);
            } else if (wmsLocTail.getProgress() >= 60) {
                countStsMap.put("60",countStsMap.get("60") + 1);
            }else if (wmsLocTail.getProgress() >= 40) {
                countStsMap.put("40",countStsMap.get("40") + 1);
            }else if (wmsLocTail.getProgress() >= 20) {
                countStsMap.put("20",countStsMap.get("20") + 1);
            }else {
                countStsMap.put("less20",countStsMap.get("less20") + 1);
            }
        }
        ArrayList<Object> tailStsNames = new ArrayList<>(countStsMap.keySet());
        ArrayList<Object> tailStsCount = new ArrayList<>(countStsMap.values());

        HashMap<String, ArrayList<Object>> result = new HashMap<>();
        result.put("name",tailStsNames);
        result.put("count",tailStsCount);
        return R.ok(result);
    }
}
