package com.example.nationalgeographicproject.configoration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ArticleFinalProjectConfig {
    @Bean
    public ModelMapper modelMapper(){ return new ModelMapper();}
}
