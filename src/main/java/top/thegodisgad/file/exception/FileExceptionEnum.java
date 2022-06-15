package top.thegodisgad.file.exception;

import top.thegodisgad.exception.BaseExceptionInfoSet;

/**
 *
 * @author yhdhz
 */

public enum FileExceptionEnum implements BaseExceptionInfoSet {
    /**
     * 上传文件失败
     */
    UPLOAD_FILE_FAIl(300L,"上传文件失败","UPLOAD_FILE_FAIl")
    ;

    /**
     * 错误编号
     */
    private final Long index;
    /**
     * 错误信息
     */
    private final String message;
    /**
     * 错误码
     */
    private final String code;
    FileExceptionEnum(Long index, String message, String code) {
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
}
