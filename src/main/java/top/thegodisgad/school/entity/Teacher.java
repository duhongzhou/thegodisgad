package top.thegodisgad.school.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 就读于
 * @TableName tb_teacher
 */
@Data
public class Teacher implements Serializable {
    /**
     * 自增标识,必为主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;

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

    private static final long serialVersionUID = 1L;
}