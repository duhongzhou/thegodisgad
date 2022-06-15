package top.thegodisgad.blog.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 博客文章操作
 *
 * @TableName tb_blog_operation
 */
@Data
public class BlogOperation implements Serializable {
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
    private Integer oldOperationType;
    /**
     * 是有效的;1:有效;0:无效;
     */
    @NotBlank(message = "博客操作有效性不能为空")
    private Integer isValid;
}