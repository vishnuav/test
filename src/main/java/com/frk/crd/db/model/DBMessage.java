package com.frk.crd.db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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