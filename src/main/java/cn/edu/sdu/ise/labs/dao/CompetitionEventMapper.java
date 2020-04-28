package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.model.CompetitionEvent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 比赛项目数据查询组件
 *
 * @author 李桢煜
 * @date 2020/3/11 21:18
 */
public interface CompetitionEventMapper {
    /**
     * 主键id
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入比赛项目数据
     *
     * @param record
     * @return
     */
    int insert(CompetitionEvent record);

    /**
     * 根据主键选择
     *
     * @param id
     * @return
     */
    CompetitionEvent selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(CompetitionEvent record);

    /**
     * 获取比赛项目下拉列表
     *
     * @param competitionEventName 比赛项目名称
     * @return 下拉列表
     */
    List<CompetitionEvent> listByName(@Param("competitionEventName") String competitionEventName);

    /**
     * 给获取运动员详情返回参数中返回比赛项目名称使用
     *
     * @param competitionEventCode 比赛项目编码
     * @return 比赛项目名称
     */
    CompetitionEvent getByCode(@Param("competitionEventCode") String competitionEventCode);
}