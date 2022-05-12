package top.thegodisgad.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 表用户信息修改日志注释
 *
 * @TableName tb_user_information_log
 */
@Data
public class UserInformationLog implements Serializable {
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
     * 操作人
     */
    private Long operator;

    /**
     * 用户标识
     */
    private Long userId;
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 注册来源;1:手机;2:邮箱;3:用户名;4:第三方
     */
    private Integer registerSource;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 手机绑定时间
     */
    private Date mobileBindTime;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机绑定时间
     */
    private Date emailBindTime;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 头像
     */
    private Long headPortrait;

    /**
     * 状态;1:正常;2:封禁;0:注销;
     */
    private Integer status;

    /**
     * 证书;密码
     */
    private String certificate;
    /**
     * 旧密码
     */
    private String oldCertificate;


    public User getUser() {
        User user = new User();
        user.setUserId(this.userId);
        user.setUserName(this.userName);
        user.setNickName(this.nickName);
        user.setStatus(this.status);
        user.setMobile(this.mobile);
        user.setEmail(this.email);
        user.setGender(this.gender);
        user.setHeadPortrait(this.headPortrait);
        user.setStatus(this.status);
        return user;
    }

    public void setUser(Long operator, User user) {
        if (operator == null || this.operator == null) {
            this.operator = user.getUserId();
        } else {

            this.operator = operator;
        }
        this.userId = user.getUserId();
        this.nickName = user.getNickName();
        this.registerSource = user.getRegisterSource();
        this.userName = user.getUserName();
        this.mobile = user.getMobile();
        this.mobileBindTime = user.getMobileBindTime();
        this.email = user.getEmail();
        this.emailBindTime = user.getEmailBindTime();
        this.gender = user.getGender();
        this.birthday = user.getBirthday();
        this.headPortrait = user.getHeadPortrait();
        this.status = user.getStatus();
    }
    public UserAuthorized getUserAuthorized() {
        UserAuthorized userAuthorized = new UserAuthorized();
        userAuthorized.setUserId(this.userId);
        userAuthorized.setCertificate(this.certificate);
        userAuthorized.setIsValid(this.status);
        return userAuthorized;
    }
    public UserAuthorized getUserAuthorizedForName() {
        UserAuthorized result = null;
        if (this.userName != null) {
            result = getUserAuthorized();
            result.setIdentityType(1);
            result.setIdentifier(this.userName);
        }
        return result;
    }

    public UserAuthorized getUserAuthorizedForMobile() {
        UserAuthorized result = null;
        if (this.mobile != null) {
            result = getUserAuthorized();
            result.setIdentityType(2);
            result.setIdentifier(this.mobile);
        }

        return result;
    }

    public UserAuthorized getUserAuthorizedForEmail() {
        UserAuthorized result = null;
        if (this.email != null) {
            result = getUserAuthorized();
            result.setIdentityType(3);
            result.setIdentifier(this.email);
        }
        return result;
    }

    public void setUserAuthorized(Long operator, UserAuthorized userAuthorized) {
        if (operator == null || this.operator == null) {
            this.operator = userAuthorized.getUserId();
        } else {

            this.operator = operator;
        }
        this.userId = userAuthorized.getUserId();
        this.certificate = userAuthorized.getCertificate();
        this.status = userAuthorized.getIsValid();
    }

    private static final long serialVersionUID = 1L;


}