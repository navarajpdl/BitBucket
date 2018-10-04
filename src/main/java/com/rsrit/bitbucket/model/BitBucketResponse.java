package com.rsrit.bitbucket.model;


public class BitBucketResponse {
    private int status;
    private String bitBucketData;

    public BitBucketResponse() {
    }

    public BitBucketResponse(int status, String bitBucketData) {
        this.status = status;
        this.bitBucketData = bitBucketData;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBitBucketData() {
        return bitBucketData;
    }

    public void setBitBucketData(String bitBucketData) {
        this.bitBucketData = bitBucketData;
    }
}
