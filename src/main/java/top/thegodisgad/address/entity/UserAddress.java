package top.thegodisgad.address.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 拥有的地址,所有地址
 * @TableName tb_user_address
 */
@Data
public class UserAddress implements Serializable {
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
     * 地址标识
     */
    private Long addressId;

    /**
     * 是有效的;1:有效;0:无效;
     */
    private Integer isValid;

    private static final long serialVersionUID = 1L;
}