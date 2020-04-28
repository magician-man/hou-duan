package cn.edu.sdu.ise.labs.controller;

import cn.edu.sdu.ise.labs.model.ResultContext;
import cn.edu.sdu.ise.labs.service.CompetitionEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门管理后端服务模块
 *
 * @author 李桢煜
 * @description
 * @date 2020/4/1 15:19
 */
@RestController
@RequestMapping("competitionEvent")
public class CompetitionEventController {
    @Autowired
    private CompetitionEventService competitionEventService;

    @GetMapping("listByName")
    public ResultContext listByName(String competitionEventName) {
        return ResultContext.returnSuccess(competitionEventService.listByName(competitionEventName));
    }
}
