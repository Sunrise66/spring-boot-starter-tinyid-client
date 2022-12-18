package com.gtihub.sunrise.tinyid.base.entity;

/**
 * @author du_imba
 */
public class  ResultCode {

    /**
     * 正常可用
     */
    public static final int NORMAL = 1;
    /**
     * 需要重新加载nextId
     */
    public static final int LOADING = 2;
    /**
     * 超过maxId
     */
    public static final int OVER = 3;

    private ResultCode(){

    }
}
