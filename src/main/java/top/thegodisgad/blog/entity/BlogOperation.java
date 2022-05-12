package top.thegodisgad.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客文章操作
 * @TableName tb_blog_operation
 */
@Data
public class BlogOperation implements Serializable {
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
     * 用户标识(博主)
     */
    private Long userId;

    /**
     * 贴子标识
     */
    private Long blogId;

    /**
     * 操作类型 1,查看;2,好评;3,差评;4,评论
     */
    private Integer operationType;

    /**
     * 是有效的;1:有效;0:无效;
     */
    private Integer isValid;

    private static final long serialVersionUID = 1L;
}