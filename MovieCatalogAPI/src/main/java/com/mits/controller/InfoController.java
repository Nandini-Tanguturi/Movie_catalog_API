package com.mits.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mits.dto.ApiInfoResponse;

@RestController
public class InfoController {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String version;

    @GetMapping("/info")
    public ResponseEntity<ApiInfoResponse> getInfo() {

        ApiInfoResponse response = new ApiInfoResponse();

        response.setAppName(appName);
        response.setVersion(version);
        response.setMessage("Movie Catalog API is Running Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}