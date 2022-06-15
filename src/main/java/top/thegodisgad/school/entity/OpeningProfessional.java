package top.thegodisgad.school.entity;

import lombok.Data;
import top.thegodisgad.school.group.SchoolGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 开设专业
 * @TableName tb_opening_professional
 */
@Data
public class OpeningProfessional implements Serializable {
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
     * 学院标识
     */
    private Long openingProfessionalId;
    /**
     * 学院标识
     */
    @NotNull(groups = {SchoolGroup.add.class,SchoolGroup.delete.class},message = "学院标识不能为空")

    private Long academyId;

    /**
     * 专业标识
     */
    @NotNull(groups = {SchoolGroup.add.class,SchoolGroup.delete.class},message = "专业标识不能为空")

    private Long professionalId;

    private static final long serialVersionUID = 1L;
}