package com.zhs.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.common.entity.WmsLocHead;
import com.zhs.common.entity.WmsLocTail;
import com.zhs.common.service.IWmsLocTailService;
import com.zhs.system.entity.User;
import com.zhs.system.service.UsersService;
import com.zhs.system.utils.EmailService;
import com.zhs.system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zhs.system.config.BaseController;

import java.util.*;

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
    @Autowired
    private UsersService usersService;
    @Autowired
    private EmailService emailService;

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


    @RequestMapping("/notice")
    public R noticeUsers(){
        StringBuffer content = new StringBuffer();
        List<WmsLocTail> locTails = locTailService.list(new QueryWrapper<WmsLocTail>()
                .le("m_expired_date", new Date()));
        List<User> users = usersService.list();

        if (locTails.size() <= 0 || users.size() <= 0) {
            return R.error("There are no user or no expired item");
        }
        content.append("<table border=\"1\">\n" +
                "  <caption>\n" +
                "    Expired Items\n" +
                "  </caption>\n" +
                "  <tr>\n" +
                "    <th scope=\"col\" align=\"center\">Location Number</th>\n" +
                "    <th scope=\"col\" align=\"center\">Material Name</th>\n" +
                "    <th scope=\"col\" align=\"center\">Amount</th>\n" +
                "    <th scope=\"col\" align=\"center\">Expired Date</th>\n" +
                "  </tr>");
        for (WmsLocTail wmsLocTail : locTails) {
            content.append("<tr>\n" +
                    "    <th scope=\"row\" align=\"center\">"+wmsLocTail.getLocNo()+"</th>\n" +
                    "    <td align=\"center\">"+wmsLocTail.getName()+"</td>\n" +
                    "    <td align=\"center\">"+wmsLocTail.getQty()+"</td>\n" +
                    "    <td align=\"center\">"+wmsLocTail.getExpiredDate$()+"</td>\n" +
                    "  </tr>");
        }
        content.append("</table>");
        for (User user : users) {
            emailService.sendEmail(user.getEmail(),"Hello, admin. here is some expired items",content.toString());
        }
        return R.ok("The system had sent all expired items to all users.");
    }
}
