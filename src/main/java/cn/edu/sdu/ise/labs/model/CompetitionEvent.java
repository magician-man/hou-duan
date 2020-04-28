package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.util.Date;

/**
 * 比赛项目实体对象
 *
 * @author 李桢煜
 * @date 2020/3/14 22:13
 */
@Data
public class CompetitionEvent {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 比赛项目编码
     */
    private String competitionEventCode;
    /**
     * 比赛项目名称
     */
    private String competitionEventName;
    /**
     * 组别
     */
    private Integer suitType;
    /**
     * 运动员编码
     */
    private String athleteCode;
    /**
     * 比赛开始时间
     */
    private Date planStartAt;
    /**
     * 比赛结束时间
     */
    private Date planEndAt;
    /**
     * 状态
     */
    private Integer status;
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