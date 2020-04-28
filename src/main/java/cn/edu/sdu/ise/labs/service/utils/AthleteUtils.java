package cn.edu.sdu.ise.labs.service.utils;

import cn.edu.sdu.ise.labs.dao.AthleteMapper;
import cn.edu.sdu.ise.labs.dao.CompetitionEventMapper;
import cn.edu.sdu.ise.labs.dao.TeamMapper;
import cn.edu.sdu.ise.labs.dto.AthleteDTO;
import cn.edu.sdu.ise.labs.dto.AthleteUpdateDTO;
import cn.edu.sdu.ise.labs.model.Athlete;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.vo.AthleteVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author 李桢煜
 * @date 2020/3/15 15:11
 */
public class AthleteUtils {
    @Autowired
    private static AthleteMapper athleteMapper;
    private static CompetitionEventMapper competitionEventMapper;
    private static TeamMapper teamMapper;


    /**
     * 将实体对象转换为VO对象
     *
     * @param athlete 实体对象
     * @return VO对象
     */
    public static AthleteVO convertToVO(Athlete athlete) {
        AthleteVO athleteVO = new AthleteVO();
        BeanUtils.copyProperties(athlete, athleteVO);
        athleteVO.setCompetitionEventName(competitionEventMapper.getByCode(athlete.getCompetitionEventCode()).getCompetitionEventName());
        athleteVO.setTeamName(teamMapper.getByCode(athlete.getTeamCode()).getTeamName());
        athleteVO.setUpdatedAt(FormatUtils.formatFullDate(athlete.getUpdatedAt()));
        return athleteVO;
    }

    public static void validateAthlete(AthleteDTO athleteDTO) {
        Assert.isTrue(athleteMapper.countRegCode(athleteDTO) == 0, "运动员注册号已存在");
        Assert.isTrue(athleteMapper.countCompetitionEventCode(athleteDTO) >= 1, "比赛项目不存在");
    }

    public static void validateAthlete2(AthleteUpdateDTO athleteDTO) {
        Assert.isTrue(athleteMapper.countRegCode2(athleteDTO) == 0, "运动员注册号已存在");
        Assert.isTrue(athleteMapper.countCompetitionEventCode2(athleteDTO) >= 1, "比赛项目不存在");
    }
}
