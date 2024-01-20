package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.StateDTO;
import com.newswebsite.main.entity.State;
import com.newswebsite.main.exception.StateCodeNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.StateRepo;
import com.newswebsite.main.service.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StateService implements IStateService {

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private MessageSource msg;

    private final CollectionMapper mapper = new CollectionMapper();

    @Override
    public StateDTO findByStateCode(String stateCode) {
        State state = stateRepo.findByCode(stateCode);
        if (state == null)
            throw new StateCodeNotFoundException(msg.getMessage("state.not.found", null, null) + stateCode);
        return mapper.map(state, StateDTO.class);
    }

    @Override
    public Map<String, String> findAll() {
        return mapper.map(stateRepo.findAll(), State::getCode, State::getName);
    }

    @Override
    public StateDTO save(StateDTO stateDTO) {
        State state = mapper.map(stateDTO, State.class);
        return mapper.map(stateRepo.save(state), StateDTO.class);
    }
}
