//Programmer Name: ZHU HANGSHUAI
//Program Name: WMS
//Description: Warehouse Management System
//First Written on: May/29/2023
//Edited on: January 2024

package com.zhs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Boot {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

}
