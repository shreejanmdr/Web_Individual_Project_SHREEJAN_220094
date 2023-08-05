package com.system.shreejanEcommerce.service.impl;

import com.system.shreejanEcommerce.entity.Queries;
import com.system.shreejanEcommerce.pojo.QueriesPojo;
import com.system.shreejanEcommerce.repo.QueriesRepo;
import com.system.shreejanEcommerce.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryServiceImpl implements QueryService {
    private final QueriesRepo queriesRepo;

    @Override
    public List<Queries> fetchAll() {
        return this.queriesRepo.findAll();
    }

    @Override
    public String save(QueriesPojo queriesPojo) {
        Queries queries=new Queries();
        queries.setName(queriesPojo.getName());
        queries.setEmail(queriesPojo.getEmail());
        queries.setSubject(queriesPojo.getSubject());
        queries.setMessage(queriesPojo.getMessage());
        queriesRepo.save(queries);
        return "saved";
    }
}
