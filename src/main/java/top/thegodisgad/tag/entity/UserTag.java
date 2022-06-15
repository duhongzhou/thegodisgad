package top.thegodisgad.tag.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 拥有的标签(所有标签)
 * @TableName tb_user_tag
 */
@Data
public class UserTag implements Serializable {
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
     * 拥有者标识
     */
    private Long userId;

    /**
     * 标签标识
     */
    private Long tagId;

    /**
     * 是有效的;1:有效;0:无效;
     */
    private Integer isValid;

    private static final long serialVersionUID = 1L;
}