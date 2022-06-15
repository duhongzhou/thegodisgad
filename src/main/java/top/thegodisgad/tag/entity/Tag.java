package top.thegodisgad.tag.entity;

import lombok.Data;
import top.thegodisgad.tag.group.TagGroup;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

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
    @NotBlank(groups = TagGroup.SelectCreator.class,message = "用户id不能未空!")
    private Long creatorId;

    /**
     * 标签标识
     */
    @NotBlank(groups = TagGroup.SelectTagId.class,message = "标签标识不能为空!")
    private Long tagId;

    /**
     * 标签名
     */
    @NotBlank(groups = {TagGroup.Add.class,TagGroup.SelectTagName.class},message = "标签名不能为空!")

    private String tagName;

    /**
     * 标签属性
     */
    @NotBlank(groups = TagGroup.Add.class,message = "标签内容不能为空!")
    private String tagProperty;

    /**
     * 是有效的;1:有效;0:无效;
     */
    private Integer isValid;

    private static final long serialVersionUID = 1L;
}