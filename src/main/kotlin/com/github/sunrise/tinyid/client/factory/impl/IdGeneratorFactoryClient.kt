package com.github.sunrise.tinyid.client.factory.impl


import com.gtihub.sunrise.tinyid.base.factory.AbstractIdGeneratorFactory
import com.gtihub.sunrise.tinyid.base.generator.IdGenerator
import com.gtihub.sunrise.tinyid.base.generator.impl.CachedIdGenerator
import com.gtihub.sunrise.tinyid.base.service.SegmentIdService
import org.springframework.stereotype.Component

@Component
class IdGeneratorFactoryClient(val segmentIdService: SegmentIdService) : AbstractIdGeneratorFactory() {

    override fun createIdGenerator(bizType: String): IdGenerator {
        return CachedIdGenerator(bizType, segmentIdService)
    }

}