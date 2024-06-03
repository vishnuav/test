package com.frk.crd.db.service.impl;

import com.frk.crd.db.dao.DBExceptionMessageRepository;
import com.frk.crd.db.dao.StreamedMessageRepository;
import com.frk.crd.db.model.DBStreamedMessage;
import com.frk.crd.db.model.ExceptionMessage;
import com.frk.crd.db.model.StreamedMessage;
import com.frk.crd.db.service.DBWriteService;
import com.frk.crd.model.IExceptionMessage;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DBWriteServiceImpl implements DBWriteService {
  private final StreamedMessageRepository streamedMessageRepository;
  private final DBExceptionMessageRepository exceptionMessageRepository;

  public DBWriteServiceImpl(StreamedMessageRepository streamedMessageRepository,
      DBExceptionMessageRepository exceptionMessageRepository) {
    this.streamedMessageRepository = streamedMessageRepository;
    this.exceptionMessageRepository = exceptionMessageRepository;
  }

  @Override
  public List<? extends StreamedMessage> saveMessages(Collection<String> messages) {
    if (ObjectUtils.isEmpty(messages)) {
      log.warn("Cannot save empty messages");
      return List.of();
    }
    List<DBStreamedMessage> streamedMessages = messages.stream().parallel().map(DBStreamedMessage::new).collect(Collectors.toList());
    streamedMessageRepository.saveAll(streamedMessages);
    return streamedMessages;
  }

  @Override
  public List<? extends IExceptionMessage> saveExceptionMessages(Collection<IExceptionMessage> messages) {
    if (ObjectUtils.isEmpty(messages)) {
      log.warn("Cannot save empty exception messages");
      return List.of();
    }
    List<ExceptionMessage> exceptionMessages = messages.stream().parallel().map(ExceptionMessage::new).collect(Collectors.toList());
    exceptionMessageRepository.saveAll(exceptionMessages);
    return exceptionMessages;
  }
}