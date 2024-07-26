package com.frk.crd.db.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "streamed_message")
public class DBStreamedMessage implements StreamedMessage {
  @Id
  private long messageDateTime;
  private String message;

  public DBStreamedMessage(long messageDateTime, String message) {
    this.messageDateTime = messageDateTime;
    this.message = message;
  }

  public DBStreamedMessage(String message) {
    this.messageDateTime = System.currentTimeMillis();
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    DBStreamedMessage that = (DBStreamedMessage) o;
    return messageDateTime == that.messageDateTime && Objects.equals(message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageDateTime, message);
  }
}