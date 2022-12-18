package com.github.sunrise.tinyid.client.utils

import com.github.sunrise.tinyid.client.factory.impl.IdGeneratorFactoryClient
import org.springframework.stereotype.Component

@Component
class TinyId(private val client: IdGeneratorFactoryClient) {

    fun nextId(bizType: String?): Long {
        if (bizType.isNullOrEmpty()) {
            throw IllegalArgumentException("type is null")
        }
        val idGenerator = client.getIdGenerator(bizType)
        return idGenerator.nextId()
    }

    fun nextId(bizType: String?, batchSize: Int? = null): List<Long> {
        if (bizType.isNullOrEmpty()) {
            throw IllegalArgumentException("type is null")
        }
        val idGenerator = client.getIdGenerator(bizType)
        if (null == batchSize) {
            return listOf(idGenerator.nextId())
        }
        return idGenerator.nextId(batchSize)
    }
}