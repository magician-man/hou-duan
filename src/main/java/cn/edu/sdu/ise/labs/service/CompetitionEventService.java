package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.vo.CompetitionEventVO;

import java.util.List;

/**
 * 比赛项目业务接口
 *
 * @author 李桢煜
 * @date 2020/3/15 17：55
 */
public interface CompetitionEventService {
    /**
     * 根据比赛名称获取下拉列表
     *
     * @param competitionEventName 比赛名称（模糊匹配）
     * @return 比赛列表
     */
    List<CompetitionEventVO> listByName(String competitionEventName);
}
