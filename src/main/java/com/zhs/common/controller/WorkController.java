package com.zhs.common.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
            newTail.setMClassifyName(material.getClassifyName());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, material.getExpiredDay());
            Date expiredDate = calendar.getTime();
            newTail.setMExpiredDate(expiredDate);

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
}
