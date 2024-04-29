package com.frk.crd.db.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class DBStreamedMessageTest {
  private static final String TEXT = "text";

  @Test
  void testEquals() {
    // When both message date time and text are same
    final long now = System.currentTimeMillis();
    DBStreamedMessage message = new DBStreamedMessage(now, TEXT);
    DBStreamedMessage other = new DBStreamedMessage(now, TEXT);
    Assertions.assertEquals(message, other);

    // When only message date time is same and text is not same
    final String not_text = "not-text";
    other = new DBStreamedMessage(now, not_text);
    Assertions.assertNotEquals(message, other);

    // When message date time is not same and text is same
    other = new DBStreamedMessage(now + 1, TEXT);
    Assertions.assertNotEquals(message, other);

    // When both message date time and text are not same
    other = new DBStreamedMessage(now + 1, not_text);
    Assertions.assertNotEquals(message, other);
  }
}