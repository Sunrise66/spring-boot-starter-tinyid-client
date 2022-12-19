package com.github.sunrise.tinyid.client.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.github.sunrise.tinyid.client")
@EnableConfigurationProperties(TinyIdClientConfig::class)
class TinyIdClientAutoConfiguration