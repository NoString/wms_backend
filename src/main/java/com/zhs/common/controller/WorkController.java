package com.zhs.common.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhs.common.entity.WmsLocHead;
import com.zhs.common.entity.WmsLocTail;
import com.zhs.common.entity.WmsMaterial;
import com.zhs.common.service.IWmsLocHeadService;
import com.zhs.common.service.IWmsLocTailService;
import com.zhs.common.service.IWmsMaterialService;
import com.zhs.system.utils.Check;
import com.zhs.system.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private IWmsLocHeadService headService;
    @Autowired
    private IWmsMaterialService materialService;
    @Autowired
    private IWmsLocTailService tailService;

    @PostMapping("/putin")
    @Transactional
    public R putIn(@RequestBody ArrayList<WmsLocTail> locTails,
                   HttpServletRequest request){
        //合并所有库位号和物料号一致的物料
        HashMap<String, WmsLocTail> map = new HashMap<>();
        for (WmsLocTail locTail : locTails) {
            String key = locTail.getLocNo() + "=" + locTail.getMaterialId();
            if (map.containsKey(key)){
                WmsLocTail exist = map.get(key);
                exist.setQty(exist.getQty() + locTail.getQty());
            }else {
                map.put(key,locTail);
            }
        }
        ArrayList<WmsLocTail> cleanLocTails = new ArrayList<>(map.values());
        for (WmsLocTail cleanLocTail : cleanLocTails) {
            WmsLocHead head = headService.getOne(new QueryWrapper<WmsLocHead>()
                    .eq("loc_no", cleanLocTail.getLocNo()));
            if (Check.isEmpty(head)){
                return R.error("Location Number: " + cleanLocTail.getLocNo() + " is not exist.");
            }
            if (!head.getLocSts().equals("E.Empty Storage")) {
                return R.error("Location Number: " + cleanLocTail.getLocNo() + " is not available.");
            }
            head.setLocSts("U.Used Storage");
            headService.updateById(head);

            WmsMaterial material = materialService.getOne(new QueryWrapper<WmsMaterial>()
                    .eq("id", cleanLocTail.getMaterialId()));
            if (Check.isEmpty(material)) {
                return R.error("The material: " + cleanLocTail.getName() + " is not exist. Please, add the material first.");
            }

            Date date = new Date();
            String username = request.getHeader("username");
            WmsLocTail newTail = new WmsLocTail();
            newTail.setQty(cleanLocTail.getQty());
            newTail.setLocNo(cleanLocTail.getLocNo());
            newTail.setMaterialId(material.getId());
            newTail.setName(material.getName());
            newTail.setCode(material.getCode());
            newTail.setClassifyName(material.getClassifyName());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, material.getExpiredDay());
            Date expiredDate = calendar.getTime();
            newTail.setExpiredDate(expiredDate);

            newTail.setMWeight(material.getWeight());
            newTail.setMColor(material.getColor());
            newTail.setMUnit(material.getUnit());
            newTail.setMMemo(material.getMemo());
            newTail.setUpdateTime(date);
            newTail.setUpdateBy(username);
            newTail.setCreateTime(date);
            newTail.setCreateBy(username);

            tailService.save(newTail);
        }
        return R.ok("All data had added the database.");
    }


    @PostMapping("/moveOut")
    @Transactional
    public R moveOut(@RequestBody ArrayList<WmsLocTail> locTails,
                     HttpServletRequest request){

        /**
         * 先把所有需要出库且库位相同的数据合并到一个map里
         * 然后遍历这个map, 每遍历完一个库位,判断明细表是否还有该库位的明细,如果没有明细,将库位状态改为"空库位"
         */
        HashMap<String, ArrayList<WmsLocTail>> map = new HashMap<>();
        for (WmsLocTail locTail : locTails) {
            if (!map.containsKey(locTail.getLocNo())) {
                map.put(locTail.getLocNo(), new ArrayList<WmsLocTail>());
            }
            map.get(locTail.getLocNo()).add(locTail);
        }

        for (String key : map.keySet()) {
            ArrayList<WmsLocTail> combineByLocNoTails = map.get(key);
            for (WmsLocTail locTail : combineByLocNoTails) {
                WmsLocTail target = tailService.getById(locTail);
                if (Check.isEmpty(target)) {
                    return R.error("The location number is " + target.getLocNo() + ", the material name is " + target.getName() + ", which is not exist in database. \n try to refresh the page to get newest data.");
                }
                tailService.removeById(locTail);
            }
            long locNoCount = tailService.count(new QueryWrapper<WmsLocTail>()
                    .eq("loc_no", key));
            if (locNoCount <= 0) {
                headService.update(new UpdateWrapper<WmsLocHead>()
                        .eq("loc_no", key)
                        .set("loc_sts","E.Empty Storage"));
            }
        }
        return R.ok("All selected data had remove from the database.");
    }
}
