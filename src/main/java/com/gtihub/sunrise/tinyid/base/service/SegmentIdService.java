package com.gtihub.sunrise.tinyid.base.service;


import com.gtihub.sunrise.tinyid.base.entity.SegmentId;

/**
 * @author du_imba
 */
public interface SegmentIdService {

    /**
     * 根据bizType获取下一个SegmentId对象
     * @param bizType
     * @return
     */
    SegmentId getNextSegmentId(String bizType);

}
