package cn.edu.sdu.ise.labs.vo;

import lombok.Data;

/**
 * @author 李桢煜
 * @description
 * @date 2020/3/11 23:49
 */
@Data
public class AthleteVO {

    /**
     * 运动员编码
     */
    private String athleteCode;
    /**
     * 队员注册编码
     */
    private String regCode;
    /**
     * 队伍编码
     */
    private String teamCode;
    /**
     * 队伍名称
     */
    private String teamName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 比赛项目编码
     */
    private String competitionEventCode;
    /**
     * 比赛项目名称
     */
    private String competitionEventName;
    /**
     * 更新时间
     */
    private String updatedAt;


    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private String createdAt;
    /**
     *
     */
    private Integer idType;
    /**
     *
     */
    private String idNumber;

}
