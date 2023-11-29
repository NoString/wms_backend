package com.zhs.common.controller;

import com.zhs.common.entity.param.PieParam;
import com.zhs.common.mapper.WmsTailInRecordMapper;
import com.zhs.system.utils.DateFormat;
import com.zhs.system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zhs.system.config.BaseController;

import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZHU HANGSHUAI
 * @since 2023-11-29
 */
@RestController
@RequestMapping("/common/wmsTailInRecord")
public class WmsTailInRecordController extends BaseController {

    @Autowired
    private WmsTailInRecordMapper inRecordMapper;

    @RequestMapping("/weekCount")
    public R listWeekCount(){
        ArrayList<Integer> counts = new ArrayList<>();
        String[] recentSevenDays = DateFormat.getRecentSevenDays();
        for (String recentSevenDay : recentSevenDays) {
            Integer count = inRecordMapper.countByDate(recentSevenDay);
            counts.add(count);
        }
        return R.ok(counts);
    }

    @RequestMapping("/classifyCount")
    public R listClassifyCount(){
        ArrayList<PieParam> pieParams = inRecordMapper.groupByClassify();
        return R.ok(pieParams);
    }

    @RequestMapping("/userCount")
    public R listUserCount(){
        ArrayList<PieParam> pieParams = inRecordMapper.groupByUsername();
        return R.ok(pieParams);
    }
}
