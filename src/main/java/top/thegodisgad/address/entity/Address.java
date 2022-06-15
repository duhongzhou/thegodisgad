package top.thegodisgad.address.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 地址
 *
 * @TableName tb_address
 */
@Data
public class Address implements Serializable {
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
     * 地址标识
     */
    private Long userId;
    /**
     * 国家
     */
    private String country;
    /**
     * 省(自治区,直辖市)
     */
    @NotBlank
    private String province;
    /**
     * 地级市
     */
    @NotBlank
    private String city;
    /**
     * 县(市,区)
     */
    @NotBlank
    private String county;
    /**
     * 镇(乡,街道)
     */
    @NotBlank
    private String street;
    /**
     * 详细地址(村<小区>,组<楼>,号 >
     */
    private String detailedAddress;
}