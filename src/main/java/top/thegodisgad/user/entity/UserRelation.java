package top.thegodisgad.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 关系
 * @TableName tb_user_relation
 */
@Data
public class UserRelation implements Serializable {
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
     * 用户标识(主人)
     */
    private Long userId;

    /**
     * 用户标识(好友)
     */
    private Long friendId;

    /**
     * 是拉黑;1:是;0:不是;
     */
    private Integer isShield;

    /**
     * 是有效的;1:有效;0:无效;
     */
    private Integer isValid;

    private static final long serialVersionUID = 1L;


}