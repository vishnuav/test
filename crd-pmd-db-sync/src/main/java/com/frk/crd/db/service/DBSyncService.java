package com.frk.crd.db.service;

import com.frk.crd.model.IExceptionMessage;

import java.util.Collection;

public interface DBSyncService {

  void syncStreamedMessages(Collection<String> messages);

  void syncExceptionMessages(Collection<IExceptionMessage> messages);

  long getLastDBSaveTime();

  long getLastDBSyncTime();
}