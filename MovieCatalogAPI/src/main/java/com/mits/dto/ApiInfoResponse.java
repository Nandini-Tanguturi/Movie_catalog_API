package com.mits.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiInfoResponse {

    private String appName;

    private String version;

    private String message;

}