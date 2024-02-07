package com.newswebsite.main.service.roleservice;

import com.newswebsite.main.dto.RoleDTO;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleReader implements IRoleReader {

    private final RoleRepo roleRepo;

    private final CollectionMapper mapper;

    @Autowired
    public RoleReader(RoleRepo roleRepo, CollectionMapper mapper) {
        this.roleRepo = roleRepo;
        this.mapper = mapper;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return mapper.map(roleRepo.findAll(), RoleDTO.class);
    }
}
