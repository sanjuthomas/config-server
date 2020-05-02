package com.sanjuthomas.config.server.api;

import lombok.Data;

/**
 * @author Sanju Thomas
 */


@Data
public class ConfigResponse {

  private final String application;
  private final String profile;
  private final String key;
  private final Object value;

}
