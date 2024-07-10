package com.frk.crd.sync.service;

import com.frk.crd.core.IExceptionMessage;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface DBSyncService {
  @Transactional
  void syncStreamedMessages(Collection<String> messages);

  @Transactional
  void syncExceptionMessages(Collection<IExceptionMessage> messages);

  long getLastDBSaveTime();

  long getLastDBSyncTime();
}