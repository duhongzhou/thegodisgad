package top.thegodisgad.user.entity;

import lombok.Data;
import top.thegodisgad.utils.user.UserUtils;
import top.thegodisgad.utils.validation.UserName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author yhdhz
 * @TableName tb_user
 */
@Data
public class User implements Serializable {
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
    @UserName
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
     * 邮箱绑定时间
     */
    private Date emailBindTime;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 性别;1:男;0:女;2:未知
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

    public void registered()
    {
        createId();
        createName(this.id);
    }
    public void createId()
    {

        if(this.userId==null)
        {
            this.userId= UserUtils.createId();
        }else{
            if(this.userId==0)
            {
                this.userId= UserUtils.createId();
            }
        }
    }
    public void createName(Long id)
    {
        if(this.userName==null)
        {
            this.userName=UserUtils.creatName(id);
        }else{
            if (this.userName.isEmpty()) {
                this.userName=UserUtils.creatName(id);
            }
        }
    }
    private static final long serialVersionUID = 1L;
}