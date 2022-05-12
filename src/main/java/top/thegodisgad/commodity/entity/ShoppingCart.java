package top.thegodisgad.commodity.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 购物车
 * @TableName tb_shopping_cart
 */
@Data
public class ShoppingCart implements Serializable {
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
     * 商品标识
     */
    private Long commodityId;

    /**
     * 购买数量
     */
    private Object number;

    /**
     * 是有效的;1:有效;0:无效;
     */
    private Integer isValid;

    private static final long serialVersionUID = 1L;
}