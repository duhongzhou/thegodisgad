package top.thegodisgad.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录日志
 * @author yhdhz
 * @TableName tb_user_login_log
 */
@Data
public class UserLoginLog implements Serializable {
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
     * 标识符;账号;手机;邮箱
     */
    private String identifier;

    /**
     * 证书;密码,登录成功时不保存
     */
    private String certificate;

    /**
     * 登录状态;1:成功;2:账号不存在;3:密码错误;
     */
    private Integer loginStatus;

    /**
     * 登录ip
     */
    private String loginIp;

    private static final long serialVersionUID = 1L;
}