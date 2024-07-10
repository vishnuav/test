package com.frk.crd.sync.dao;

import com.frk.crd.sync.model.DBStreamedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamedMessageRepository extends JpaRepository<DBStreamedMessage, Long> {
}