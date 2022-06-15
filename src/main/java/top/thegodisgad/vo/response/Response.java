package top.thegodisgad.vo.response;

import com.alibaba.fastjson.JSONObject;
import top.thegodisgad.exception.BaseExceptionInfoGet;

public class Response {
    private String code;
    private String message;
    private Object result;
    public Response() {
    }
    /**
     * 默认成功构造函数
     */
    public Response(String code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    /**
     *
     */
    public Response(Object result) {
        this.result = result;
    }


    /**
     * 请求成功
     */
    public static Response succeed(Object result){
        Response response = new Response(result);
        response.setCode("-1");
        response.setMessage("请求成功!");
        return response;
    }
    /**
     *
     * @description: 请求成功,需要成功信息
     *
     * @author 杜洪洲
     * @date 2022/5/22 15:11
     * @return top.thegodisgad.vo.response.Response
     * @throw
     */
    public static Response succeed(Object result,String message){

        Response response = new Response(result);
        response.setCode("-1");
        response.setMessage(message);
        return response;
    }
    /**
     * 请求失败
     */
    public static Response failed(BaseExceptionInfoGet baseExceptionInfoGet){
        Response response = new Response();
        response.setCode(String.valueOf(baseExceptionInfoGet.getIndex()));
        response.setMessage(baseExceptionInfoGet.getMessage());
        return response;
    }
    public static Response failed(String code,String message){
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
    public static Response failed(Object result,String message){
        Response response = new Response(result);
        response.setCode("8888");
        response.setMessage(message);
        return response;
    }
    public static Response failed(Object result,String code,String message){
        Response response = new Response(result);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
    public static Response failed(String message){
        Response response = new Response();
        response.setCode("9999");
        response.setMessage(message);
        return response;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
