package com.TaMIS.TaMISValidator;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.SosReaderEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.SosReaderServiceImp;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Iterator;
import java.util.List;

import static org.quartz.JobBuilder.newJob;
//for setting the path


@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
public class Application {



    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

    }

    @Bean
    public CacheManager cacheManager(){
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        return cacheManager;
    }



}
