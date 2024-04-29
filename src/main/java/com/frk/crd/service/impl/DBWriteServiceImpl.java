package com.frk.crd.service.impl;

import com.frk.crd.db.dao.StreamedMessageRepository;
import com.frk.crd.db.model.DBStreamedMessage;
import com.frk.crd.db.model.StreamedMessage;
import com.frk.crd.service.DBWriteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DBWriteServiceImpl implements DBWriteService {
  private final StreamedMessageRepository streamedMessageRepository;

  public DBWriteServiceImpl(StreamedMessageRepository streamedMessageRepository) {
    this.streamedMessageRepository = streamedMessageRepository;
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
}