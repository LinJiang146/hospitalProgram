package com.wei.order.config;

import javafx.scene.image.Image;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;



@Configuration
@MapperScan("com.wei.order.mapper")
public class OrderConfig {

}
