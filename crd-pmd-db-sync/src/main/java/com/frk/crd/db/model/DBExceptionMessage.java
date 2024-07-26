package com.frk.crd.db.model;

import com.frk.crd.core.IExceptionMessage;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exception_message")
public class DBExceptionMessage implements IExceptionMessage {
  @Id
  private long id;
  @Column(name = "source_name")
  private String sourceName;
  @Column(name = "destination_type")
  private String destinationType;
  @Column(name = "destination_url")
  private String destinationURL;
  @Column(name = "payload")
  private String payload;
  @Column(name = "status")
  private String status;
  @Column(name = "exception_date_time")
  private long exceptionDateTime;

  public DBExceptionMessage(IExceptionMessage message) {
    this(message.getId(), message.getSourceName(), message.getDestinationType(), message.getDestinationURL(), message.getPayload(),
        message.getStatus(), message.getExceptionDateTime());
  }
}