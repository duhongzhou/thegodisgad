package top.thegodisgad.commodity.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * @TableName tb_commodity
 */
@Data
public class Commodity implements Serializable {
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
     * 发布者标识
     */
    private Long userId;

    /**
     * 商品标识
     */
    private Long commodityId;

    /**
     * 贴子标识
     */
    private Long blogId;

    /**
     * 商品名
     */
    private String commodityName;

    /**
     * 商品状态;1:上架;2:下架;0:删除
     */
    private Integer commodityStatus;

    /**
     * 商品类型;1:实物;0:虚拟物品;
     */
    private Integer commodityType;

    /**
     * 商品编码
     */
    private String commodityCode;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Object inventoryNumber;

    /**
     * 想要人数(下单)
     */
    private Object wantNumber;

    private static final long serialVersionUID = 1L;
}