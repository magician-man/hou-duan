package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

/**
 * @author 李桢煜
 * @description
 * @date 2020/3/11 23:00
 */
@Data
public class AthleteDTO {
    /**
     * 姓名
     */
    private String name;
    /**
     * 注册编码
     */
    private String regCode;
    /**
     * 队伍编码
     */
    private String teamCode;
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
}
