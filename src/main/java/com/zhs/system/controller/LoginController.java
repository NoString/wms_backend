package com.zhs.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.common.entity.WmsLocTail;
import com.zhs.common.service.IWmsLocTailService;
import com.zhs.system.config.BaseController;
import com.zhs.system.entity.User;
import com.zhs.system.entity.UserLogin;
import com.zhs.system.service.LoginService;
import com.zhs.system.service.UsersService;
import com.zhs.system.utils.Check;
import com.zhs.system.utils.EmailService;
import com.zhs.system.utils.R;
import com.zhs.system.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhs.system.utils.Constant.*;

@RestController
@RequestMapping("/user")
public class LoginController extends BaseController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private IWmsLocTailService locTailService;





    @PostMapping("/login")
    public R login(@RequestBody Map<String,Object> params){


        User user = usersService.getOne(new QueryWrapper<User>()
                .eq("username", params.get("username")));
        if (user == null) {
            return R.parse(USER_10001);
        }
        if (!user.getPassword().equals(params.get("password"))){
            return R.parse(USER_10003);
        }else if (!user.isStatus()){
            return R.parse(USER_10002);
        }
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(user.getId());
        userLogin.setToken(TokenUtils.token(user.getUsername(), user.getPassword()));
        userLogin.setCreateTime(new Date());
        loginService.saveOrUpdate(userLogin,new QueryWrapper<UserLogin>()
                .eq("user_id", user.getId()));
        user.setLastLogin(new Date());
        usersService.saveOrUpdate(user);
        Map<String, Object> res = new HashMap<>();
        res.put("username", user.getUsername());
        res.put("token", userLogin.getToken());
        res.put("nickname", user.getNickname());
        res.put("id",user.getId());
        List<WmsLocTail> list = locTailService.list(new QueryWrapper<WmsLocTail>()
                .le("m_expired_date", new Date()));
        if (!Check.isEmpty(user.getEmail()) && list.size() > 0) {
            StringBuffer content = new StringBuffer();
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
            for (WmsLocTail wmsLocTail : list) {
                content.append("<tr>\n" +
                        "    <th scope=\"row\" align=\"center\">"+wmsLocTail.getLocNo()+"</th>\n" +
                        "    <td align=\"center\">"+wmsLocTail.getName()+"</td>\n" +
                        "    <td align=\"center\">"+wmsLocTail.getQty()+"</td>\n" +
                        "    <td align=\"center\">"+wmsLocTail.getExpiredDate$()+"</td>\n" +
                        "  </tr>");
            }
            content.append("</table>");
            emailService.sendEmail(user.getEmail(),"Hello, admin. here is some expired items",content.toString());
        }
        return R.ok(res);
    }
}
