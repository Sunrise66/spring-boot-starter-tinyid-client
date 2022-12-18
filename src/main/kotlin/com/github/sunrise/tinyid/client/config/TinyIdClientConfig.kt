package com.github.sunrise.tinyid.client.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "tinyid.client")
open class TinyIdClientConfig {
    var token: String = ""
    var server: String = ""
    val readTimeout: Int = 5000
    val connectTimeout: Int = 5000
}