package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.dao.CompetitionEventMapper;
import cn.edu.sdu.ise.labs.model.CompetitionEvent;
import cn.edu.sdu.ise.labs.service.CompetitionEventService;
import cn.edu.sdu.ise.labs.service.utils.CompetitionEventUtils;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.vo.CompetitionEventVO;
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
public class CompetitionEventServiceImpl implements CompetitionEventService {
    @Autowired
    private CompetitionEventMapper competitionEventMapper;


    /**
     * 根据队伍名称获取下拉列表
     *
     * @param competitionEventName 队伍名称（模糊匹配）
     * @return 队伍列表
     */
    @Override
    public List<CompetitionEventVO> listByName(String competitionEventName) {
        competitionEventName = FormatUtils.makeFuzzySearchTerm(competitionEventName);
        List<CompetitionEvent> competitionEventList = competitionEventMapper.listByName(competitionEventName);
        return competitionEventList.stream()
                .map(item -> CompetitionEventUtils.convertToVO(item))
                .collect(Collectors.toList());
    }
}
