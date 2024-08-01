package com.frk.crd.db.dao;

import com.frk.crd.db.model.DBSecurity;

import java.util.List;

public interface DBBroadcastSecurityRepository {
  DBSecurity getSecurityForOrderId(String orderId);

  DBSecurity getSecurityForSecId(String secId);

  List<String> getChildSecurities(String secId);
}