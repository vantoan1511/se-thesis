package com.newswebsite.main.service.stateservice;

import com.newswebsite.main.dto.StateDTO;

import java.util.Map;

public interface IStateReader {

    StateDTO findByStateCode(String stateCode);

    Map<String, String> findAll();

}
