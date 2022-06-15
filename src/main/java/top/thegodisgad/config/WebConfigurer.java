package top.thegodisgad.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import top.thegodisgad.utils.interceptor.LocaleInterceptor;

import javax.servlet.MultipartConfigElement;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author yhdhz
 */
@Configuration
@EnableCaching
//@EnableWebMvc
//@Import(org.springframework.web.WebApplicationInitializer.class) 会使默认配置失效
public class WebConfigurer implements WebMvcConfigurer {

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            /**
             * @param target
             * @param method
             * @param params
             * @return
             */
            @Override
            public Object generate(Object target, java.lang.reflect.Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /*国际化 start*/
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        //LocaleInterceptor
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleInterceptor();
        //指定参数
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        //指定默认语言为中文
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }

    @Bean
    public LocalValidatorFactoryBean mvcValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.getValidationPropertyMap().put("hibernate.validator.fail_fast", "true");
        //为Validator配置国际化
        localValidatorFactoryBean.setValidationMessageSource(resourceBundleMessageSource());
        return localValidatorFactoryBean;
    }

    //    上传文件大小
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        //  单个数据大小
        factory.setMaxFileSize(DataSize.parse("500MB"));
        // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.parse("500MB"));
        return factory.createMultipartConfig();
    }

    @Bean(name = "messageSource")
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        //指定国际化的Resource Bundle地址
        resourceBundleMessageSource.setBasename("i18n/messages");
        //指定国际化的默认编码
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }
    //    @Bean
    //    public LoginInterceptor loginInterceptor() {
    //        //LocaleInterceptor
    //        LoginInterceptor loginInterceptor = new LoginInterceptor();
    //        //指定参数
    //
    //        return loginInterceptor;
    //    }
    /**
     * websocket
     */
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }
    private static final List<String> EXCLUDE_PATH = Arrays.asList("/", "/view/**", "/api/user/login/", "/api/user/login_get/**", "/css/**", "/js/**", "/img/**", "json/**", "/fonts/**", "/*.html");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor())
//                //拦截的路径
//                .addPathPatterns("/**")
//                .excludePathPatterns(EXCLUDE_PATH);改用aop实现
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");

    }

    /**
     * 跨域
     *
     * @return
     */
//    @Bean
//    public CorsFilter corsFilter() {
//        //1.添加CORS配置信息
//        CorsConfiguration config = new CorsConfiguration();
//        //1) 允许的域,不要写*，否则cookie就无法使用了
//        config.addAllowedOriginPattern("http://10.90.246.218:8080");
//        //2) 是否发送Cookie信息
//        config.setAllowCredentials(true);
//        //3) 允许的请求方式GET POST等
//        config.addAllowedMethod("*");
//        // 4）允许的头信息
//        config.addAllowedHeader("*");
//        config.setMaxAge(3600L);
//
//        //2.添加映射路径，我们拦截一切请求
//        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
//        configSource.registerCorsConfiguration("/**", config);
//
//        //3.返回新的CorsFilter.
//        return new CorsFilter(configSource);
//    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("10.90.246.218:8080")/*报此错误 When allowCredentials is true, allowedOrigins cannot contain the special value "*" since that cannot be set on the "Access-Control-Allow-Origin" response header. To allow credentials to a set of origins, list them explicitly or consider using "allowedOriginPatterns" instead.
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }


}
