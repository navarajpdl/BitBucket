package com.rsrit.bitbucket.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.ResponseEntity;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BitBucketDetailResponse {
    private List<ResponseEntity> responseEntityList;

    public BitBucketDetailResponse(List<ResponseEntity> responseEntityList) {
        this.responseEntityList = responseEntityList;
    }

    public List<ResponseEntity> getResponseEntityList() {
        return responseEntityList;
    }

    public void setResponseEntityList(List<ResponseEntity> responseEntityList) {
        this.responseEntityList = responseEntityList;
    }
}
