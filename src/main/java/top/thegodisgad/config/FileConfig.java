package top.thegodisgad.config;

import java.io.File;

/**
 * @author yhdhz
 */
public class FileConfig {
    public static  final String BASE_SAVE_PATH="G:\\2022spring\\网络编程实践\\thegodisgad\\upload";
    public static File save(String fileName){
       return new File(BASE_SAVE_PATH,fileName);
    }
}
