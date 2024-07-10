package com.frk.crd.sync.rest.impl;

import com.frk.crd.sync.rest.DBServicesRestController;
import com.frk.crd.sync.service.DBReadService;
import com.frk.crd.sync.service.DBSyncService;
import com.frk.crd.core.IExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
public class DBServicesRestControllerImpl implements DBServicesRestController {
  private final DBSyncService dbSyncService;
  private final DBReadService dbReadService;

  public DBServicesRestControllerImpl(DBSyncService dbSyncService, DBReadService dbReadService) {
    this.dbSyncService = dbSyncService;
    this.dbReadService = dbReadService;
  }

  @Override
  public ResponseEntity<Integer> syncMessages(Collection<IExceptionMessage> messages) {
    dbSyncService.syncExceptionMessages(messages);
    int size = ObjectUtils.isNotEmpty(messages) ? messages.size() : 0;
    log.info("Synced {} streamed messages", size);
    return ResponseEntity.ok(ObjectUtils.isNotEmpty(messages) ? messages.size() : 0);
  }

  @Override
  public ResponseEntity<List<IExceptionMessage>> fetchAllExceptionMessages() {
    return ResponseEntity.ok(dbReadService.fetchAllExceptionMessages());
  }

  @Override
  public ResponseEntity<List<IExceptionMessage>> fetchExceptionMessagesForDate(long exceptionDate) {
    return ResponseEntity.ok(dbReadService.fetchExceptionMessagesForDate(exceptionDate));
  }
}