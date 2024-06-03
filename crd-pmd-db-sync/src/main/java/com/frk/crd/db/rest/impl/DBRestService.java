package com.frk.crd.db.rest.impl;

import com.frk.crd.db.rest.DBServicesRestController;
import com.frk.crd.db.service.DBSyncService;
import com.frk.crd.model.IExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
public class DBRestService implements DBServicesRestController {
  private final DBSyncService dbSyncService;

  public DBRestService(DBSyncService dbSyncService) {
    this.dbSyncService = dbSyncService;
  }

  @Override
  public ResponseEntity<Integer> syncMessages(Collection<IExceptionMessage> messages) {
    dbSyncService.syncExceptionMessages(messages);
    int size = ObjectUtils.isNotEmpty(messages) ? messages.size() : 0;
    log.info("Synced {} streamed messages", size);
    return ResponseEntity.ok(ObjectUtils.isNotEmpty(messages) ? messages.size() : 0);
  }
}