package com.frk.crd.db.dao;

import com.frk.crd.db.model.DBBroadcastSecurity;

public interface DBBroadcastSecurityRepository {
  DBBroadcastSecurity getSecurity(String secId);
}