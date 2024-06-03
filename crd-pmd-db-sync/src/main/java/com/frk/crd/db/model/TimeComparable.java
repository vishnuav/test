package com.frk.crd.db.model;

import com.frk.crd.utilities.CRDUtils;
import java.util.Comparator;
import org.apache.commons.lang3.ObjectUtils;

public interface TimeComparable extends Comparable<TimeComparable>, Comparator<TimeComparable> {
  long getComparableTime();

  default long getComparableDate() {
    return CRDUtils.removeHours(getComparableTime());
  }

  @Override
  default int compare(TimeComparable o1, TimeComparable o2) {
    return ObjectUtils.compare(o1.getComparableTime(), getComparableTime(), true);
  }

  default int compareTo(TimeComparable o) {
    return ObjectUtils.compare(o.getComparableTime(), getComparableTime());
  }
}