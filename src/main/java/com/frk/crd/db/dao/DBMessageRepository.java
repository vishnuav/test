package com.frk.crd.db.dao;

import com.frk.crd.db.model.DBMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBMessageRepository extends JpaRepository<DBMessage, Long> {
}