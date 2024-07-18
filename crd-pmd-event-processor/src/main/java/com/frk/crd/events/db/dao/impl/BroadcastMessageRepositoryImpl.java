package com.frk.crd.events.db.dao.impl;

import com.frk.crd.events.db.dao.BroadcastMessageRepository;
import com.frk.crd.events.model.BroadcastAllocation;
import com.frk.crd.events.model.BroadcastOrder;
import com.frk.crd.events.model.BroadcastSecurity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class BroadcastMessageRepositoryImpl implements BroadcastMessageRepository {
  private static final String GET_SECURITY = "";
  private static final String GET_ORDER = "";
  private static final String GET_ALLOCATIONS = "";
  private final JdbcTemplate jdbcTemplate;

  public BroadcastMessageRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public String fetchSecurityId(String orderId) {
    if (StringUtils.isBlank(orderId)) {
      log.error("Order id cannot be blank or empty to fetch security id");
      return null;
    } else {
      return StringUtils.EMPTY;
    }
  }

  @Override
  public List<BroadcastSecurity> fetchUnderlyingSecurityId(String secId) {
    if (StringUtils.isBlank(secId)) {
      log.error("Sec id cannot be blank or empty to fetch underlying security");
      return List.of();
    } else {
      return List.of();
    }
  }

  @Override
  public List<BroadcastSecurity> fetchSecurity(String secId) {
    if (StringUtils.isBlank(secId)) {
      log.error("Sec id cannot be blank or empty to fetch security");
      return List.of();
    } else {
      log.info("Getting Security for {}", secId);
      List<String> security = new ArrayList<>();
      jdbcTemplate.query(GET_SECURITY,
        preparedStatementSetter -> {
          preparedStatementSetter.setString(1, secId);
        },
        resultSet -> {
          final String message = resultSet.getString("message");
          if (StringUtils.isNotBlank(message)) {
            security.add(message);
          }
        });
      log.info("Found Order for order id {}", secId);
      return null;
    }
  }

  @Override
  public BroadcastOrder fetchOrder(String orderId) {
    if (StringUtils.isBlank(orderId)) {
      log.error("Order id cannot be blank or empty to fetch order");
      return null;
    } else {
      log.info("Getting order for {}", orderId);
      List<String> order = new ArrayList<>();
      jdbcTemplate.query(GET_ORDER,
        preparedStatementSetter -> {
          preparedStatementSetter.setString(1, orderId);
        },
        resultSet -> {
          final String message = resultSet.getString("message");
          if (StringUtils.isNotBlank(message)) {
            order.add(message);
          }
        });
      log.info("Found Order for order id {}", orderId);
      return null;
    }
  }

  @Override
  public List<BroadcastAllocation> fetchAllocations(String orderId) {
    if (StringUtils.isBlank(orderId)) {
      log.error("Order id cannot be blank or empty to fetch allocations");
      return List.of();
    } else {
      log.info("Getting allocations for {}", orderId);
      List<String> allocations = new ArrayList<>();
      jdbcTemplate.query(GET_ALLOCATIONS,
        preparedStatementSetter -> {
          preparedStatementSetter.setString(1, orderId);
        },
        resultSet -> {
          final String message = resultSet.getString("message");
          if (StringUtils.isNotBlank(message)) {
            allocations.add(message);
          }
        });
      log.info("Found {} Allocations for order id {}", allocations.size(), orderId);
      return List.of();
    }
  }
}