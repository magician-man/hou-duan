package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

/**
 * @author 李桢煜
 * @description
 * @date 2020/3/11 23:00
 */
@Data
public class AthleteQueryDTO {
    /**
     * 姓名
     */
    private String name;
    /**
     * 队伍编码
     */
    private String teamCode;
    /**
     * 运动员注册编码
     */
    private String regCode;
    /**
     * 证件号码
     */
    private String idNumber;
    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页记录数
     */
    private Integer pageSize;
}
