package com.frk.crd.db.dao;

import com.frk.crd.db.model.ExceptionMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBExceptionMessageRepository extends JpaRepository<ExceptionMessage, Long> {
}