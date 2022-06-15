package top.thegodisgad.school.entity;

import lombok.Data;
import top.thegodisgad.school.group.SchoolGroup;
import top.thegodisgad.user.entity.UserInformationLog;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 就读于
 *
 * @TableName tb_student
 */
@Data
public class Student implements Serializable {
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
     * 用户标识
     */
    private Long userId;
    /**
     * student_id 学号
     */
    private Long studentId;
    /**
     * 学校标识
     */
//    @NotNull(groups = {SchoolGroup.add.class, SchoolGroup.batch.class}, message = "学校不能为空")
    private Long schoolId;
    private String schoolName;
    /**
     * 学院标识
     */
//    @NotNull(groups = {SchoolGroup.add.class, SchoolGroup.batch.class}, message = "学院不能为空")
    private Long academyId;
    private String academyName;
    /**
     * 专业标识
     */
//    @NotNull(groups = {SchoolGroup.add.class, SchoolGroup.batch.class}, message = "专业不能为空")
    private Long professionalId;
    private String professionalName;
    /**
     * 年级
     */
    private Object grade;
    /**
     * 届
     */
    private Object period;
    /**
     * 证书
     */
    @NotBlank(groups = {SchoolGroup.batch.class}, message = "密码不能为空")
    private String certificate;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String nickName;

    public UserInformationLog getUserInformationLog() {
        UserInformationLog userInformationLog = new UserInformationLog();
        userInformationLog.setGender(this.gender);
        userInformationLog.setCertificate(this.certificate);
        userInformationLog.setNickName(this.nickName);
        return userInformationLog;
    }
}