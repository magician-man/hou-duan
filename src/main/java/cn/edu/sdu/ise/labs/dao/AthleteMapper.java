package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.dto.AthleteDTO;
import cn.edu.sdu.ise.labs.dto.AthleteQueryDTO;
import cn.edu.sdu.ise.labs.dto.AthleteUpdateDTO;
import cn.edu.sdu.ise.labs.model.Athlete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 运动员数据访问组件
 *
 * @author 李桢煜
 * @date 2020/3/11 20:39
 */

public interface AthleteMapper {
    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入运动员数据
     *
     * @param record
     * @return
     */
    int insert(Athlete record);

    /**
     * 根据主键选择
     *
     * @param id
     * @return
     */
    Athlete selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新
     *
     * @param record 运动员对象
     * @return 主键
     */
    int updateByPrimaryKey(Athlete record);


    /**
     * 根据运动员编码获取运动员信息详情
     *
     * @param athleteCode 运动员编码
     * @return 运动员信息详情
     */
    Athlete getByCode(String athleteCode);

    /**
     * 根据查询条件获取运动员列表
     *
     * @param queryDTO 查询条件
     * @param offset   开始位置
     * @param limit    记录数量
     * @return
     */
    List<Athlete> list(@Param("queryDTO") AthleteQueryDTO queryDTO,
                       @Param("offset") Integer offset,
                       @Param("limit") Integer limit);

    /**
     * 根据查询条件获取命中个数
     *
     * @param queryDTO 查询条件
     * @return 命中数量
     */
    Integer count(
            @Param("queryDTO") AthleteQueryDTO queryDTO);

    /**
     * 根据查询条件获取命中个数
     *
     * @param DTO 查询条件
     * @return 命中数量
     */
    Integer countRegCode(
            @Param("DTO") AthleteDTO DTO);

    /**
     * 根据查询条件获取命中个数
     *
     * @param DTO 查询条件
     * @return 命中数量
     */
    Integer countCompetitionEventCode(
            @Param("DTO") AthleteDTO DTO);


    /**
     * 根据查询条件获取命中个数
     *
     * @param DTO 查询条件
     * @return 命中数量
     */
    Integer countRegCode2(
            @Param("DTO") AthleteUpdateDTO DTO);

    /**
     * 根据查询条件获取命中个数
     *
     * @param DTO 查询条件
     * @return 命中数量
     */
    Integer countCompetitionEventCode2(
            @Param("DTO") AthleteUpdateDTO DTO);


    /**
     * 根据运动员编码获取下拉列表
     *
     * @param athleteCode 运动员编码
     * @return 详情列表
     */
    List<Athlete> listByCode(@Param("athleteCode") String athleteCode);

    /**
     * 检测athleteCode是否重复
     */
    Integer countAC(@Param("athleteCode") String athleteCode);

    /**
     * 根据代码列表批量删除运动员
     *
     * @param codeList 代码列表
     * @return 删除个数
     */
    Integer deleteByCodes(
            @Param("codeList") List<String> codeList);

}