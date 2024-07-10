package com.frk.crd.sync.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "crd_owner.ts_order")
public class TSOrder {
  @Id
  @Column(name = "order_id")
  private long orderId;
}
