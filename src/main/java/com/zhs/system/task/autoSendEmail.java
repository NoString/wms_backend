package com.zhs.system.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.common.entity.WmsLocTail;
import com.zhs.common.service.IWmsLocTailService;
import com.zhs.system.entity.User;
import com.zhs.system.service.UsersService;
import com.zhs.system.utils.Check;
import com.zhs.system.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class autoSendEmail {

    @Autowired
    private IWmsLocTailService locTailService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private EmailService emailService;
    @Scheduled(cron = "0 0 0,12 * * *") // 每天凌晨触发任务
    public void myTask() {
        StringBuffer content = new StringBuffer();
        List<WmsLocTail> locTails = locTailService.list(new QueryWrapper<WmsLocTail>()
                .le("m_expired_date", new Date()));
        List<User> users = usersService.list();

        if (locTails.size() <= 0 || users.size() <= 0) {
            return;
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
    }
}
