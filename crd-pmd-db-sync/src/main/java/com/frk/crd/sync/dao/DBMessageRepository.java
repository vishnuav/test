package com.frk.crd.sync.dao;

import com.frk.crd.sync.model.DBMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBMessageRepository extends JpaRepository<DBMessage, Long> {
}