package com.frk.crd.db.service.impl;

import com.frk.crd.model.IExceptionMessage;
import com.frk.crd.db.service.DBSyncService;
import com.frk.crd.db.service.DBWriteService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DBSyncServiceImpl implements DBSyncService {
  private final DBWriteService writeService;
  final List<String> streamedMessages = Collections.synchronizedList(new ArrayList<>());
  final List<IExceptionMessage> exceptionMessages = Collections.synchronizedList(new ArrayList<>());
  final AtomicLong lastDBSyncTime = new AtomicLong(0);
  final AtomicLong lastDBSaveTime = new AtomicLong(0);

  public DBSyncServiceImpl(DBWriteService writeService) {
    this.writeService = writeService;
  }

  @Override
  public void syncStreamedMessages(Collection<String> messages) {
    if (ObjectUtils.isNotEmpty(messages)) {
      this.lastDBSyncTime.set(System.currentTimeMillis());
      this.streamedMessages.addAll(messages);
    }
  }

  @Override
  public void syncExceptionMessages(Collection<IExceptionMessage> messages) {
    if (ObjectUtils.isNotEmpty(messages)) {
      this.lastDBSyncTime.set(System.currentTimeMillis());
      this.exceptionMessages.addAll(messages);
    }
  }

  @Override
  @Scheduled(cron = "${crd.app.db.sync.save.cron}")
  public void saveToDB() {
    StopWatch started = StopWatch.createStarted();
    saveMessages();
    saveExceptionMessages();
    this.lastDBSaveTime.set(System.currentTimeMillis());
    log.trace("Time taken for saveToDB {} millis", started.getTime(TimeUnit.MILLISECONDS));
  }

  @Override
  public long getLastDBSaveTime() {
    return this.lastDBSaveTime.get();
  }

  @Override
  public long getLastDBSyncTime() {
    return this.lastDBSyncTime.get();
  }

  @PreDestroy
  public void preDestroy() {
    saveToDB();
  }

  void saveMessages() {
    if (ObjectUtils.isNotEmpty(streamedMessages)) {
      StopWatch started = StopWatch.createStarted();
      try {
        synchronized (streamedMessages) {
          writeService.saveMessages(streamedMessages);
          log.debug("Saved {} messages - in {} millis", streamedMessages.size(),
              started.getTime(TimeUnit.MILLISECONDS));
          streamedMessages.clear();
        }
      } catch (Throwable e) {
        // TODO: 12/26/2021 Need a better way than to catch a generic exception - exception listener maybe!?!
        log.error("Unable to save streamed messages", e);
      } finally {
        // This must be in the finally to manage memory better
        started.stop();
        log.trace("SaveMessages completed in {} millis", started.getTime(TimeUnit.MILLISECONDS));
      }
    } else {
      log.trace("No Messages to add to DB");
    }
  }

  void saveExceptionMessages() {
    if (ObjectUtils.isNotEmpty(exceptionMessages)) {
      StopWatch started = StopWatch.createStarted();
      try {
        synchronized (exceptionMessages) {
          writeService.saveExceptionMessages(exceptionMessages);
          log.debug("Saved {} exception messages - in {} millis", exceptionMessages.size(),
              started.getTime(TimeUnit.MILLISECONDS));
          exceptionMessages.clear();
        }
      } catch (Throwable e) {
        // TODO: 12/26/2021 Need a better way than to catch a generic exception - exception listener maybe!?!
        log.error("Unable to save streamed messages", e);
      } finally {
        // This must be in the finally to manage memory better
        started.stop();
        log.trace("saveExceptionMessages completed in {} millis", started.getTime(TimeUnit.MILLISECONDS));
      }
    } else {
      log.trace("No Exception Messages to add to DB");
    }
  }
}