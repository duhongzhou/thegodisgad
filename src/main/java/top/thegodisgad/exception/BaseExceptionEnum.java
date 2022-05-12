package top.thegodisgad.exception;

public enum BaseExceptionEnum implements BaseExceptionInfoSet{
    BASE_EXCEPTION_ENUM(0L,"未知错误","Unknown");
    private final Long index;
    /**
     * 错误信息
     */
    private final  String message;
    /**
     * 错误码
     */
    private final  String code;
    BaseExceptionEnum(Long index, String message, String code){
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
        return "UserExceptionEnum{" +
                "index=" + index +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
