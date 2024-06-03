package com.frk.crd.db.dao;

import com.frk.crd.db.model.DBMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBMessageRepository extends JpaRepository<DBMessage, Long> {
}