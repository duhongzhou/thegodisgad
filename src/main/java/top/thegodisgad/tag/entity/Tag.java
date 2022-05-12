package top.thegodisgad.tag.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 标签
 * @TableName tb_tag
 */
@Data
public class Tag implements Serializable {
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
    private Long creatorId;

    /**
     * 标签标识
     */
    private Long tagId;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 标签属性
     */
    private String tagProperty;

    /**
     * 是有效的;1:有效;0:无效;
     */
    private Integer isValid;

    private static final long serialVersionUID = 1L;
}