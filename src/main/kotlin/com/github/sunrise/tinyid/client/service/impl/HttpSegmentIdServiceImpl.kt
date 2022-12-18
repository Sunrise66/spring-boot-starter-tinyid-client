package com.github.sunrise.tinyid.client.service.impl

import com.github.sunrise.tinyid.client.config.TinyIdClientConfig
import com.gtihub.sunrise.tinyid.base.entity.SegmentId
import com.gtihub.sunrise.tinyid.base.service.SegmentIdService
import com.github.sunrise.tinyid.client.utils.TinyIdHttpUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.text.MessageFormat
import java.util.concurrent.atomic.AtomicLong
import kotlin.random.Random

@Service
class HttpSegmentIdServiceImpl(val tinyIdClientConfig: TinyIdClientConfig) : SegmentIdService {

    private final val logger = LoggerFactory.getLogger(javaClass)
    private final val serverUrl = "http://{0}/tinyid/id/nextSegmentIdSimple?token={1}&bizType="

    override fun getNextSegmentId(bizType: String): SegmentId? {
        val url = chooseService(bizType)
        val response = TinyIdHttpUtils.post(url, tinyIdClientConfig.readTimeout, tinyIdClientConfig.connectTimeout)
        logger.info("tinyId client getNextSegmentId end, response:$response")
        if (response == null || "" == response.trim()) {
            return null
        }
        val segmentId = SegmentId()
        val arr = response.split(",")
        segmentId.currentId = AtomicLong(arr[0].toLong())
        segmentId.loadingId = arr[1].toLong()
        segmentId.maxId = arr[2].toLong()
        segmentId.delta = arr[3].toInt()
        segmentId.remainder = arr[4].toInt()
        return segmentId
    }

    private fun chooseService(bizType: String): String {
        val tinyIdServers = tinyIdClientConfig.server.split(",").toMutableList()
        val serverList =
            tinyIdServers.map { server -> MessageFormat.format(serverUrl, server, tinyIdClientConfig.token) }
                .toMutableList()
        var url = ""
        if (serverList.size == 1) {
            url = serverList[0]
        } else if (serverList.size > 1) {
            val random = Random(serverList.size)
            url = serverList[random.nextInt()]
        }
        url += bizType
        return url
    }
}