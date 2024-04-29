package com.frk.crd.service;

import java.util.Collection;

public interface DBSyncService {

  void syncStreamedMessages(Collection<String> message);

  void saveToDB();

  long getLastDBSaveTime();

  long getLastDBSyncTime();
}