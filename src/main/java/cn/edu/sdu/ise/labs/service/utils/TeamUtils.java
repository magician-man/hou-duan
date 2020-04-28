package cn.edu.sdu.ise.labs.service.utils;

import cn.edu.sdu.ise.labs.model.Team;
import cn.edu.sdu.ise.labs.vo.TeamVO;
import org.springframework.beans.BeanUtils;

/**
 * @author 李桢煜
 * @date 2020/3/15 17：57
 */
public class TeamUtils {
    /**
     * 将实体对象转换为VO对象
     *
     * @param team 实体对象
     * @return VO对象
     */
    public static TeamVO convertToVO(Team team) {
        TeamVO teamVO = new TeamVO();
        BeanUtils.copyProperties(team, teamVO);
        return teamVO;
    }
}
