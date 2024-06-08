package com.frk.crd.db.service.impl;

import com.frk.crd.db.dao.ExceptionMessageRepository;
import com.frk.crd.db.model.DBExceptionMessage;
import com.frk.crd.db.service.DBReadService;
import com.frk.crd.core.ExceptionMessage;
import com.frk.crd.core.IExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DBReadServiceImpl implements DBReadService {
  private final ExceptionMessageRepository exceptionMessageRepository;

  public DBReadServiceImpl(ExceptionMessageRepository exceptionMessageRepository) {
    this.exceptionMessageRepository = exceptionMessageRepository;
  }

  @Override
  public List<IExceptionMessage> fetchAllExceptionMessages() {
    List<DBExceptionMessage> allNewExceptionMessages = exceptionMessageRepository.findAllNewExceptionMessages();
    return allNewExceptionMessages.stream().map(ExceptionMessage::new).collect(Collectors.toList());
  }

  @Override
  public List<IExceptionMessage> fetchExceptionMessagesForDate(long exceptionDate) {
    return fetchAllExceptionMessages().stream().filter(v -> v.getExceptionDate() > exceptionDate).collect(Collectors.toList());
  }
}