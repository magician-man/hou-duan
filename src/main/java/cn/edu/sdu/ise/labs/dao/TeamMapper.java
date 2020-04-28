package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.model.Team;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 队伍实体对象
 *
 * @author 李桢煜
 * @date 2020/3/11 20:50
 */

public interface TeamMapper {


    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入队伍数据
     *
     * @param record
     * @return
     */
    int insert(Team record);

    /**
     * 根据主键选择
     *
     * @param id
     * @return
     */
    Team selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Team record);

    /**
     * 根据队伍名称获得队伍编码和运动员名称（模糊查询）
     *
     * @param teamName
     * @return 下拉列表
     */
    List<Team> listByName(@Param("teamName") String teamName);

    /**
     * 根据队伍编码返回队伍名称（给获取运动运动员详情接口用）
     *
     * @param teamName
     * @return
     */
    Team getByCode(@Param("teamCode") String teamName);
}