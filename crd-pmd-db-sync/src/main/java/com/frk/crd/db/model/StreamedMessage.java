package com.frk.crd.db.model;

import com.frk.crd.model.JsonAware;
import com.frk.crd.utilities.CRDUtils;

public interface StreamedMessage extends JsonAware, TimeComparable {
  long getMessageDateTime();

  default long getMessageDate() {
    return CRDUtils.removeHours(getMessageDateTime());
  }

  String getMessage();

  default long getComparableTime() {
    return getMessageDateTime();
  }
}