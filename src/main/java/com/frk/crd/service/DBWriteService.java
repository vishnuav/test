package com.frk.crd.service;


import com.frk.crd.db.model.StreamedMessage;

import java.util.Collection;
import java.util.List;

public interface DBWriteService {
  List<? extends StreamedMessage> saveMessages(Collection<String> messages);
}