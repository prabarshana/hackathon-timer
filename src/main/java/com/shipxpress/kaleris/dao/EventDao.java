package com.shipxpress.kaleris.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shipxpress.kaleris.dto.EventStatus;

@Repository
public interface EventDao extends JpaRepository<EventStatus, Long> {

}
