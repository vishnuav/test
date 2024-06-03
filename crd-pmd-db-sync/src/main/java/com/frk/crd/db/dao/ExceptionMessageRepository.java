package com.frk.crd.db.dao;

import com.frk.crd.db.model.DBExceptionMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExceptionMessageRepository extends JpaRepository<DBExceptionMessage, Long> {
  @Query("SELECT dbe FROM DBExceptionMessage dbe WHERE dbe.status = 'new'")
  List<DBExceptionMessage> findAllNewExceptionMessages();
}