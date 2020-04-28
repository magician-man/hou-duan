package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.util.Date;

/**
 * 队伍实体对象
 *
 * @author 李桢煜
 * @date 2020/3/14 22:15
 */
@Data
public class Team {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 队伍编码
     */
    private String teamCode;
    /**
     * 队伍名称
     */
    private String teamName;
    /**
     * 省份
     */
    private String province;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 描述
     */
    private String description;
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
     * 创建人
     */
    private String updatedBy;
    /**
     * 删除标记
     */
    private Boolean deleted;

}