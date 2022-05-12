package top.thegodisgad.address.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 地址
 * @TableName tb_address
 */
@Data
public class Address implements Serializable {
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
     * 地址标识
     */
    private Long addressId;

    /**
     * 国家
     */
    private String country;

    /**
     * 省(自治区,直辖市)
     */
    private String province;

    /**
     * 地级市
     */
    private String city;

    /**
     * 县(市,区)
     */
    private String county;

    /**
     * 镇(乡,街道)
     */
    private String town;

    /**
     * 详细地址(村<小区>,组<楼>,号 >
     */
    private String detailedAddress;

    private static final long serialVersionUID = 1L;
}