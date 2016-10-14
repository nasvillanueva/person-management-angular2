package io.github.gediineko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;

/**
 * Created by ggolong on 10/4/16.
 */
@Configuration
@ComponentScan("io.github.gediineko.web")
public class MvcConfig {

    @Bean
    public SortHandlerMethodArgumentResolver sortResolver() {
        return new SortHandlerMethodArgumentResolver();
    }

    @Bean
    public PageableHandlerMethodArgumentResolver pageResolver(
            SortHandlerMethodArgumentResolver sortResolver) {
        return new PageableHandlerMethodArgumentResolver(sortResolver);
    }
}
