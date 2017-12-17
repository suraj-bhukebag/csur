package com.cmpe275.project.dao;

import org.springframework.data.repository.CrudRepository;

import com.cmpe275.project.model.Station;

public interface StationDao extends CrudRepository<Station, Long> {

}
