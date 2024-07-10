package com.frk.crd.sync.model;

import java.io.Serializable;
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
@Table(name = "message")
public class DBMessage implements Serializable {
  private long id;
  private String message;

  @Id
  public long getId() {
    return id;
  }
}