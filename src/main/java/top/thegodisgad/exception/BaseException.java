package top.thegodisgad.exception;


public abstract class BaseException extends RuntimeException implements BaseExceptionInfoGet{

    protected Long index =0L;
    protected String message;
    protected String code;
    protected String baseCode = new String("BaseException");

    public BaseException(BaseExceptionInfoSet baseExceptionInfoSet) {
        super(baseExceptionInfoSet.getMessage());
        setIndex(baseExceptionInfoSet.getIndex());
        setMessage(baseExceptionInfoSet.getMessage());
        setCode(baseExceptionInfoSet.getCode());
    }

    public BaseException(Throwable cause, BaseExceptionInfoSet baseExceptionInfoSet) {
        super(baseExceptionInfoSet.getMessage(), cause);
        setIndex(baseExceptionInfoSet.getIndex());
        setMessage(baseExceptionInfoSet.getMessage());
        setCode(baseExceptionInfoSet.getCode());
    }


    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, BaseExceptionInfoSet baseExceptionInfoSet) {
        super(baseExceptionInfoSet.getMessage(), cause, enableSuppression, writableStackTrace);
        setIndex(baseExceptionInfoSet.getIndex());
        setMessage(baseExceptionInfoSet.getMessage());
        setCode(baseExceptionInfoSet.getCode());
    }

    /**
     * this.index=super.index+"."+index;
     *
     * @param index
     */
    public abstract void setIndex(Long index);

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @param code
     */
    public abstract void setCode(String code);
    @Override
    public   Long  getIndex() {
        return index;
    }

    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public String getCode() {
        return baseCode + "." + code;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "index=" + index +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", baseCode='" + baseCode + '\'' +
                '}';
    }

    /**
     * this.baseCode=super.baseCode+"."+baseCode;
     *
     * @param baseCode
     */
    protected abstract void setBaseCode(String baseCode);

}
