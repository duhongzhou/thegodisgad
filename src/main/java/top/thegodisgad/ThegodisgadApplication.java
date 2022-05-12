package top.thegodisgad;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yhdhz
 */
@MapperScan(value = "top.thegodisgad.*.mapper")//不扫包会报 Parameter 0 of method setUserBaseMapper in xxx.xxx.xx.x.* required a bean of type 'xxx.xxx.xx*.**x' that could not be found.
@SpringBootApplication //自动装配事务
//@EnableTransactionManagement //开启事务管理
public class ThegodisgadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThegodisgadApplication.class, args);
//        ConfigurableApplicationContext context= SpringApplication.run(ThegodisgadApplication.class);

//        Hello hello=context.getBean(Hello.class);
//        hello.hello();

    }

}
