package cn.edu.sdu.ise.labs.controller;

import cn.edu.sdu.ise.labs.model.ResultContext;
import cn.edu.sdu.ise.labs.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门管理后端服务模块
 *
 * @author 李洪文
 * @description
 * @date 2020/4/1 15:20
 */
@RestController
@RequestMapping("team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("listByName")
    public ResultContext listByName(String teamName) {
        return ResultContext.returnSuccess(teamService.listByName(teamName));
    }
}
