package top.thegodisgad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import top.thegodisgad.utils.interceptor.LocaleInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author yhdhz
 */
@Configuration
//@EnableWebMvc
//@Import(org.springframework.web.WebApplicationInitializer.class) 会使默认配置失效
public class WebConfigurer implements WebMvcConfigurer {

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

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    private static final List<String> EXCLUDE_PATH = Arrays.asList("/","/view/**","/api/user/login/","/api/user/login_get/**","/css/**", "/js/**", "/img/**", "json/**", "/fonts/**","/*.html");
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor())
//                //拦截的路径
//                .addPathPatterns("/**")
//                .excludePathPatterns(EXCLUDE_PATH);改用aop实现
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
