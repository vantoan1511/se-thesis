package com.newswebsite.main.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StateDTO {
    private long id;
    private String stateCode;
    private String stateName;

}
