package top.thegodisgad.school.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 就读于
 * @TableName tb_student
 */
@Data
public class Student implements Serializable {
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
     * 用户标识
     */
    private Long userId;

    /**
     * 学校标识
     */
    private Long schoolId;

    /**
     * 学院标识
     */
    private Long academyId;

    /**
     * 专业标识
     */
    private Long professionalId;

    /**
     * 年级
     */
    private Object grade;

    /**
     * 届
     */
    private Object period;

    private static final long serialVersionUID = 1L;
}