package com.zhs.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.zhs.common.entity.Classify;
import com.zhs.common.entity.WmsLocHead;
import com.zhs.common.entity.WmsMaterial;
import com.zhs.common.service.ClassifyService;
import com.zhs.common.service.IWmsMaterialService;
import com.zhs.system.annotation.ManagerAuth;
import com.zhs.system.utils.Check;
import com.zhs.system.utils.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zhs.system.config.BaseController;

import java.util.*;

import static com.zhs.system.utils.Constant.DENIED;

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
    @Autowired
    private ClassifyService classifyService;

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

    @ManagerAuth("delete classify")
    @PostMapping("/delete")
    public R delete(@RequestBody List<WmsMaterial> usersList) {
        materialService.removeBatchByIds(usersList);
        return R.ok("Successfully delete");
    }

    @ManagerAuth("add classify")
    @PostMapping("/add")
    public R add(HttpServletRequest request,
                 @RequestBody WmsMaterial item){
        Date date = new Date();
        String username = request.getHeader("username");
        if (Check.isEmpty(username)) {
            return R.parse(DENIED);
        }
        item.setCreateTime(date);
        item.setUpdateTime(date);
        item.setCreateBy(username);
        item.setUpdateBy(username);



        materialService.save(item);
        return R.ok("Successfully add.");
    }

    @ManagerAuth("edit classify")
    @PostMapping("/edit")
    public R edit(HttpServletRequest request,
                  @RequestBody WmsMaterial editObj){
        Date date = new Date();
        String username = request.getHeader("username");

        if (Check.isEmpty(username)) {
            return R.parse(DENIED);
        }

        editObj.setUpdateBy(username);
        editObj.setUpdateTime(date);
        materialService.updateById(editObj);
        return R.ok("Successfully edit");
    }

    @RequestMapping("/classify/query")
    public R queryClassify(){
        List<Classify> list = classifyService.list();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Classify classify : list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("label",classify.getName());
            map.put("value",classify.getName());
            result.add(map);
        }
        return R.ok(result);
    }

    @RequestMapping("/materialNames/query")
    public R queryMaterialNames(){
        List<WmsMaterial> list = materialService.list();
        ArrayList<HashMap<String, String>> hashMaps = new ArrayList<>();
        for (WmsMaterial material : list) {
            HashMap<String, String> map = new HashMap<>();
            map.put("label", material.getName());
            map.put("value", material.getName());
            map.put("id", String.valueOf(material.getId()));
            hashMaps.add(map);
        }
        return R.ok(hashMaps);
    }

    @RequestMapping("/id")
    public R getMaterialById(Integer id){
        WmsMaterial byId = materialService.getById(id);
        if (byId == null){
            return R.error("this material is not exist");
        }else {
            return R.ok(byId);
        }
    }
}
