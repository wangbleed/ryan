package com.ryan.boot.sample.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-11-20
 * Time: 下午1:51
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration {


}
