package com.cbc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by cbc on 2017/7/24.
 */
@Configuration
public class DateTimeFormatConfiguration extends WebMvcConfigurerAdapter{

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        //registry.addFormatter(new DateFormatter());
    }
}
