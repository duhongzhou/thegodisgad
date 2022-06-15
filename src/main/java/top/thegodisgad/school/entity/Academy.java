package top.thegodisgad.school.entity;

import lombok.Data;
import top.thegodisgad.school.group.SchoolGroup;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 学院
 *
 * @author yhdhz
 * @TableName tb_academy
 */
@Data
public class Academy implements Serializable {
    private static final long serialVersionUID = 1L;
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
    @NotBlank(groups = {SchoolGroup.add.class}, message = "学校标识不能为空!")

    private Long schoolId;
    /**
     * 学院标识
     */
    @NotBlank(groups = {SchoolGroup.update.class}, message = "学院标识不能为空!")
    private Long academyId;
    /**
     * 学院名称
     */
    @NotBlank
    private String academyName;
    /**
     * 开设专业
     */
    private List<Professional> professionals;
    /**
     * 备注
     */
    private String note;
}