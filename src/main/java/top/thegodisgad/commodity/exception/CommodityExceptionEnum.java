package top.thegodisgad.commodity.exception;

import top.thegodisgad.exception.BaseExceptionInfoSet;

/**
 * @author yhdhz
 */
public enum CommodityExceptionEnum implements BaseExceptionInfoSet {
    /**
     * 未知错误
     */
    BASE_EXCEPTION_ENUM(0L,"未知错误","Unknown"),
    /**
     * 重复添加购物车
     */
    DUPLICATE_ADDITION(300L,"重复添加购物车!","DUPLICATE_ADDITION"),
    ;
    /**
     *
     */
    private final Long index;
    /**
     * 错误信息
     */
    private final  String message;
    /**
     * 错误码
     */
    private final  String code;
    CommodityExceptionEnum(Long index, String message, String code){
        this.index=index;
        this.message=message;
        this.code=code;
    }
    @Override
    public Long getIndex() {
        return index;
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "CommodityExceptionEnum{" +
                "index=" + index +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
