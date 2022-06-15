package top.thegodisgad.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yhdhz
 */
public class RequestUtil {

    public static final String LOCAHOST_0 = "0:0:0:0:0:0:0:1";
    private static final String UNKNOWN = "unknown";

    public RequestUtil() {

    }

    public static String getIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String localhost = "localhost";

        if (localhost.equalsIgnoreCase(ip)|| LOCAHOST_0.equalsIgnoreCase(ip))
        {
            ip="172.0.0.1";
        }
        return ip;
    }
}
