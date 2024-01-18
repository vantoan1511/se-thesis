package com.newswebsite.main.service;

import com.newswebsite.main.dto.StateDTO;

import java.util.Map;

public interface IStateService {

    StateDTO findByStateCode(String stateCode);

    Map<String, String> findAll();

    StateDTO save(StateDTO stateDTO);
}
