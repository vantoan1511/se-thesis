package com.newswebsite.main.service;

import com.newswebsite.main.dto.StateDTO;

import java.util.Map;

public interface IStateReader {

    StateDTO findByStateCode(String stateCode);

    Map<String, String> findAll();

}
