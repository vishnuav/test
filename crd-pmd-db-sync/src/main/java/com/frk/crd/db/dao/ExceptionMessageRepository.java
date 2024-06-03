package com.frk.crd.db.dao;

import com.frk.crd.db.model.DBExceptionMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionMessageRepository extends JpaRepository<DBExceptionMessage, Long> {
}