package top.thegodisgad.user.exception;

import top.thegodisgad.exception.BaseExceptionInfoSet;

/**
 * @author yhdhz
 */

public enum UserExceptionEnum implements BaseExceptionInfoSet {
    // ERROR
//    USER_ERROR
//    USERID
//    IDENTIFIER
//    IDENTITY_TYPE
//    CERTIFICATE
//    REGISTER_SOURCE
//    MOBILE
//    EMAIL
//    GENDER
    UNKNOWN(1L,"未知","UNKNOWN"),
    PLEASE_LOGIN(101L,"请先登录","PLEASE_LOGIN"),
    NON_EXISTENT_USERID(100L,"用户错误","NON_EXISTENT_USERID"),
    NON_EXISTENT_USERID_FRIEND(1001L,"关系用户不存在","NON_EXISTENT_USERID.FRIEND"),
    NON_EXISTENT_IDENTIFIER(101L,"错误用户标识","NON_EXISTENT_IDENTIFIER"),
    ERROR_USERID(111L,"错误标识符","ERROR_USERID"),
    ERROR_IDENTIFIER(112L,"错误账号","ERROR_IDENTIFIER"),
    ERROR_IDENTITY_TYPE(1112L,"错误证书","ERROR_IDENTITY_TYPE"),
    ERROR_CERTIFICATE(114L,"错误证书","ERROR_CERTIFICATE"),
    ERROR_REGISTER_SOURCE(115L,"错误注册来源","ERROR_REGISTER_SOURCE"),
    ERROR_MOBILE(116L,"手机号错误","ERROR_MOBILE"),
    ERROR_EMAIL(117L,"错误邮箱","ERROR_EMAIL"),
    ERROR_GENDER(118L,"错误性别","ERROR_GENDER"),
    USERID_ISEMPTY(1210L,"帐户为空","USERID_ISEMPTY"),
    USERID_ISEMPTY_FRIEND(1211L,"关系帐户为空","USERID_ISEMPTY.FRIEND"),
    IDENTIFIER_ISEMPTY(122L,"身份类型为空","IDENTIFIER_ISEMPTY"),
    IDENTIFIER_NO_REGISTERED(123L,"账号未注册!","IDENTIFIER_NO_REGISTERED"),
    IDENTITY_TYPE_ISEMPTY(123L,"证书为空","IDENTITY_TYPE_ISEMPTY"),
    CERTIFICATE_ISEMPTY(124L,"注册源为空","CERTIFICATE_ISEMPTY"),
    REGISTER_SOURCE_ISEMPTY(125L,"手机号是空的","REGISTER_SOURCE_ISEMPTY"),
    MOBILE_ISEMPTY(126L,"电子邮件为空","MOBILE_ISEMPTY"),
    EMAIL_ISEMPTY(127L,"性别为空","EMAIL_ISEMPTY"),
    GENDER_ISEMPTY(129L,"不存在的用户名","GENDER_ISEMPTY"),
    NON_EXISTENT_INQUIRE_OPTION(201L,"没有查询项","NON_EXISTENT_INQUIRE_OPTION"),
    RELATION_IS_EXISTENT(131L,"关系已存在","RELATION_IS_EXISTENT"),

    UPDATE_ERROR(200L,"更新错误","UPDATE_ERROR"),
    UPDATE_ERROR_NO_OLD_CER(201L, "没有旧密码", "UPDATE_ERROR.NO_HAVE_OLD_CER"),
    UNKNOWN_ERROR(11L, "未知错误", "Unknown_Error");


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

    UserExceptionEnum(Long index, String message, String code) {
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
