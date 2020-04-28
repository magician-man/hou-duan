package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.vo.TeamVO;

import java.util.List;

/**
 * 队伍业务接口
 *
 * @author 李桢煜
 * @date 2020/3/11
 */

public interface TeamService {
    /**
     * 根据队伍名称获取下拉列表
     *
     * @param teamName 队伍名称（模糊匹配）
     * @return 队伍列表
     */
    List<TeamVO> listByName(String teamName);
}
