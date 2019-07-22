package com.houseWork.quartz.Task;

import com.houseWork.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * VisitsRegisterTask 访客定时任务
 * </pre>
 *
 * @author zjw
 * @date 2019/5/22 13:39.
 */
@Component
public class HelloTask {

    @Autowired
    private UserService userService;

    public void run() {
        userService.update();
    }
}
