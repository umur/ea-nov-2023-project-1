package edu.miu.ea.ap.helper.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import edu.miu.ea.ap.helper.interceptor.TokenHandlerInterceptor;
import edu.miu.ea.ap.helper.interceptor.VersionsHandlerInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    TokenHandlerInterceptor tokenHandlerInterceptor;

    @Autowired
    VersionsHandlerInterceptor versionsHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenHandlerInterceptor);
        //registry.addInterceptor(versionsHandlerInterceptor);
    }

}
