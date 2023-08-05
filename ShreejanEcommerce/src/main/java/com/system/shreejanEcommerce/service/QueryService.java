package com.system.shreejanEcommerce.service;

import com.system.shreejanEcommerce.entity.Queries;
import com.system.shreejanEcommerce.pojo.QueriesPojo;

import java.util.List;

public interface QueryService {
    List<Queries> fetchAll();

    String save(QueriesPojo queriesPojo);
}
