package top.thegodisgad.tag.exception;

import top.thegodisgad.exception.BaseExceptionInfoSet;

/**
 * @author yhdhz
 */

public enum TagExceptionEnum implements BaseExceptionInfoSet{
//    CREATOR_ID
//    TAG_ID
//    TAG_NAME
//    TAG_PROPERTY
    UNKNOWN(1L,"未知错误","UNKNOWN"),
    IS_EMPTY_TAG(110L,"标签是空的","IS_EMPTY_TAG"),
    IS_EMPTY_CREATOR_ID(112L,"创建者是空的","IS_EMPTY_CREATOR_ID"),
    IS_EMPTY_TAG_ID(112L,"标签标识是空的","IS_EMPTY_TAG_ID"),
    IS_EMPTY_TAG_NAME(112L,"标签名是空的","IS_EMPTY_TAG_NAME"),
    IS_EMPTY_TAG_PROPERTY(112L,"标签内容是空的","IS_EMPTY_TAG_PROPERTY"),


    ;
    private final Long index;
    /**
     * 错误信息
     */
    private final String message;
    /**
     * 错误码
     */
    private final String code;

    TagExceptionEnum(Long index, String message, String code) {
        this.index = index;
        this.message = message;
        this.code = code;
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
        return "UserExceptionEnum{" +
                "index=" + index +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
