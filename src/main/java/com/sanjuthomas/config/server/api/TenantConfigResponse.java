package com.sanjuthomas.config.server.api;

import lombok.Data;

/**
 * @author Sanju Thomas
 */
@Data
public class TenantConfigResponse {

  private final String tenant;
  private final String profile;
  private final Object value;

}
