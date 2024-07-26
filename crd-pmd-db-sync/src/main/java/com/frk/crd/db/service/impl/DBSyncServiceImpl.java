package com.frk.crd.db.service.impl;

import com.frk.crd.db.dao.ExceptionMessageRepository;
import com.frk.crd.db.dao.StreamedMessageRepository;
import com.frk.crd.db.model.DBExceptionMessage;
import com.frk.crd.db.model.DBStreamedMessage;
import com.frk.crd.db.service.DBSyncService;
import com.frk.crd.core.IExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class DBSyncServiceImpl implements DBSyncService {
  private final StreamedMessageRepository streamedMessageRepository;
  private final ExceptionMessageRepository exceptionMessageRepository;
  final AtomicLong lastDBSyncTime = new AtomicLong(0);
  final AtomicLong lastDBSaveTime = new AtomicLong(0);
  final AtomicLong totalSaveCount = new AtomicLong(0);
  final AtomicLong streamedMessagesSaveCount = new AtomicLong(0);
  final AtomicLong exceptionMessagesSaveCount = new AtomicLong(0);

  public DBSyncServiceImpl(StreamedMessageRepository streamedMessageRepository, ExceptionMessageRepository exceptionMessageRepository) {
    this.streamedMessageRepository = streamedMessageRepository;
    this.exceptionMessageRepository = exceptionMessageRepository;
  }

  @Override
  public void syncStreamedMessages(Collection<String> messages) {
    if (ObjectUtils.isNotEmpty(messages)) {
      this.lastDBSyncTime.set(System.currentTimeMillis());
      Flux.fromIterable(messages)
        .map(DBStreamedMessage::new)
        .collectList()
//        .doOnNext(streamedMessageRepository::saveAll)
        .doOnError(exception -> log.error("Unable to sync {} streamed messages", messages.size(), exception))
        .doOnSuccess(v -> {
          int savedCount = v.size();
          log.info("Successfully saved {} streamed messages", savedCount);
          totalSaveCount.addAndGet(v.size());
          streamedMessagesSaveCount.addAndGet(v.size());
          lastDBSaveTime.set(System.currentTimeMillis());
        })
        .doFinally(v -> log.debug("{} sync complete for streamed messages - check log for errors", messages.size()))
        .subscribe();
    } else {
      log.warn("Cannot save empty streamed messages");
    }
  }

  @Override
  public void syncExceptionMessages(Collection<IExceptionMessage> messages) {
    if (ObjectUtils.isNotEmpty(messages)) {
      this.lastDBSyncTime.set(System.currentTimeMillis());
      Flux.fromIterable(messages)
        .map(DBExceptionMessage::new)
        .collectList()
//        .doOnNext(exceptionMessageRepository::saveAll)
        .doOnError(exception -> log.error("Unable to sync {} exception messages", messages.size(), exception))
        .doOnSuccess(v -> {
          int savedCount = v.size();
          log.info("Successfully saved {} exception messages", savedCount);
          totalSaveCount.addAndGet(v.size());
          exceptionMessagesSaveCount.addAndGet(v.size());
          lastDBSaveTime.set(System.currentTimeMillis());
        })
        .doFinally(v -> log.debug("{} sync complete for exception messages - check log for errors", messages.size()))
        .subscribe();
    } else {
      log.warn("Cannot save empty exception messages");
    }
  }

  @Override
  public long getLastDBSaveTime() {
    return this.lastDBSaveTime.get();
  }

  @Override
  public long getLastDBSyncTime() {
    return this.lastDBSyncTime.get();
  }
}