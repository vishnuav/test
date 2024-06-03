package com.frk.crd.db.rest.impl;

import com.frk.crd.db.rest.DBServicesRestController;
import com.frk.crd.db.service.DBSyncService;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DBRestService implements DBServicesRestController {
  private final DBSyncService dbSyncService;

  public DBRestService(DBSyncService dbSyncService) {
    this.dbSyncService = dbSyncService;
  }

  @Override
  public ResponseEntity<Integer> syncMessages(Collection<String> messages) {
    dbSyncService.syncStreamedMessages(messages);
    int size = ObjectUtils.isNotEmpty(messages) ? messages.size() : 0;
    log.info("Synced {} streamed messages", size);
    return ResponseEntity.ok(ObjectUtils.isNotEmpty(messages) ? messages.size() : 0);
  }
}