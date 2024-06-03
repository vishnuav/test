package com.frk.crd.db.dao;

import com.frk.crd.db.model.DBStreamedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamedMessageRepository extends JpaRepository<DBStreamedMessage, Long> {
}