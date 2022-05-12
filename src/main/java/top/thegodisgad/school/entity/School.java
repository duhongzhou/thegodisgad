package top.thegodisgad.school.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 学校
 * @TableName tb_school
 */
@Data
public class School implements Serializable {
    /**
     * 自增标识,必为主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 学校标识
     */
    private Long schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 学校代码
     */
    private String schoolCode;

    /**
     * 主管部门
     */
    private String competentDepartment;

    /**
     * 省市
     */
    private String provincesCities;

    /**
     * 所在地
     */
    private String location;

    /**
     * 办学层次
     */
    private String educationLevel;

    /**
     * 备注
     */
    private String note;

    private static final long serialVersionUID = 1L;
}