package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.dao.TeamMapper;
import cn.edu.sdu.ise.labs.model.Team;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.service.TeamService;
import cn.edu.sdu.ise.labs.service.utils.TeamUtils;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.vo.TeamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 队伍业务接口实现
 *
 * @author 李桢煜
 * @date 2020/3/11
 */

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;


    /**
     * 根据队伍名称获取下拉列表
     *
     * @param teamName 队伍名称（模糊匹配）
     * @return 队伍列表
     */
    @Override
    public List<TeamVO> listByName(String teamName) {
        Token token = TokenContextHolder.getToken();
        teamName = FormatUtils.makeFuzzySearchTerm(teamName);
        List<Team> teamList = teamMapper.listByName(teamName);
        return teamList.stream()
                .map(item -> TeamUtils.convertToVO(item))
                .collect(Collectors.toList());
    }
}
