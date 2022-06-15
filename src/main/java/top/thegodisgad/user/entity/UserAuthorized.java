package top.thegodisgad.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户授权
 *
 * @author yhdhz
 * @TableName tb_user_authorized
 */
@Data
public class UserAuthorized implements Serializable {
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
     * 身份类型;1:账号;2:手机;3:邮箱;
     */
    private Integer identityType;

    /**
     * 标识符;账号;手机;邮箱
     */

    private String identifier;

    /**
     * 证书;密码
     */
    private String certificate;
    /**
     * 是有效的
     */
    private Integer isValid;
    /**
     * 验证码
     */
    private String code;

    private static final long serialVersionUID = 1L;
}