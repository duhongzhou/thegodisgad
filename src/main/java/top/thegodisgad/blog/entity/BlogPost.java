package top.thegodisgad.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客文章(贴子)
 * @TableName tb_blog_post
 */
@Data
public class BlogPost implements Serializable {
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
     * 父贴
     */
    private Long parentalBlogId;

    /**
     * 根贴标识
     */
    private Long rootBlogId;

    /**
     * 贴子标识
     */
    private Long blogId;

    /**
     * 子贴数
     */
    private Object subBlogNumber;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String value;

    /**
     * 是有效的;1:有效;0:无效;
     */
    private Integer isValid;

    /**
     * 查看人数
     */
    private Long browseNumber;

    /**
     * 评论人数
     */
    private Long reviewerNumber;

    /**
     * 评论数
     */
    private Long reviewNumber;

    /**
     * 好评数
     */
    private Long goodReview;

    /**
     * 差评数
     */
    private Long badReview;

    /**
     * 推荐权重(=好评数*0.5+查看人数*0.1-差评数*0.3)/(今天-上架
时间)
     */
    private Integer recommendedWeight;

    /**
     * 是匿名的;1:是;0:不是;
     */
    private Integer isAnonymous;

    /**
     * 公开状态遵从父贴,1全部公开,2,仅本校,3,仅粉丝,4,仅关注,5,仅好友(互相关注)
     */
    private Integer overtState;

    private static final long serialVersionUID = 1L;
}