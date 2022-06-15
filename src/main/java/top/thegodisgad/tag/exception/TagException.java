package top.thegodisgad.tag.exception;

import top.thegodisgad.exception.BaseException;
import top.thegodisgad.exception.BaseExceptionInfoSet;

public class TagException extends BaseException {
    public TagException(BaseExceptionInfoSet baseExceptionInfoSet) {
        super(baseExceptionInfoSet);
    }

    public TagException(Throwable cause, BaseExceptionInfoSet baseExceptionInfoSet) {
        super(cause, baseExceptionInfoSet);
    }

    public TagException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, BaseExceptionInfoSet baseExceptionInfoSet) {
        super(message, cause, enableSuppression, writableStackTrace, baseExceptionInfoSet);
    }

    @Override
    public void setIndex(Long index) {
        if(super.index==0){
            this.index=index;
        }else{
            if(index==0){
                this.index=index;
            }else {
                Long m = 10L;
                while (index % m != 0) {
                    m *= 10;
                }
                this.index = super.index * m + index;
            }
        }
    }

    @Override
    public void setCode(String code) {

    }

    @Override
    protected void setBaseCode(String baseCode) {
        this.baseCode=super.baseCode+"."+baseCode;
    }
}
