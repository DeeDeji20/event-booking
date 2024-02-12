package com.musala.bootstrap;

import freemarker.cache.FileTemplateLoader;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.File;
import java.io.IOException;


@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Primary
    @Bean
    public freemarker.template.Configuration getConfiguration() throws IOException {
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File("src/main/resources/templates"));
        freemarker.template.Configuration config = new freemarker.template.Configuration();
        config.setTemplateLoader(fileTemplateLoader);
        return config;
    }


}
