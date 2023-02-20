package com.grekoff.lesson4.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("secrets.properties")
//@EnableAspectJAutoProxy
//@ComponentScan("com.grekoff.lesson3")
public class AppConfig {
}
