package com.frk.crd.db.service;


import com.frk.crd.db.model.StreamedMessage;

import com.frk.crd.model.IExceptionMessage;
import java.util.Collection;
import java.util.List;

public interface DBWriteService {
  List<? extends StreamedMessage> saveMessages(Collection<String> messages);

  List<? extends IExceptionMessage> saveExceptionMessages(Collection<IExceptionMessage> messages);
}