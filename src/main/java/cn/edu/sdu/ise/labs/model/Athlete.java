package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.util.Date;


/**
 * 运动员实体对象
 *
 * @author 李桢煜
 * @date 2010/3/11 20:34
 */
@Data
public class Athlete {
    /**
     * 主键
     */
    private Integer id;
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
     * 姓名
     */
    private String name;
    /**
     * 证件号码
     */
    private String idNumber;
    /**
     * 证件类型
     */
    private Integer idType;
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
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 删除标记
     */
    private Boolean deleted;


}