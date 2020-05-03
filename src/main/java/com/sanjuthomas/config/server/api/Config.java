package com.sanjuthomas.config.server.api;

import com.fasterxml.jackson.databind.JsonNode;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Sanju Thomas
 *
 */
@RestController
public class Config {

  @Value("${config.server}")
  private String configServerUrl;

  @Value("${tenant.config.prefix}")
  private String tenantConfigPrefix;

  private WebClient webClient;

  @PostConstruct
  public void init() {
    webClient = WebClient.builder().baseUrl(configServerUrl).build();
  }

  @GetMapping("/config/{application}/{profile}/{key}")
  public Mono<ConfigResponse> configByKey(@PathVariable String application,
    @PathVariable String profile, @PathVariable String key) {
    return loadConfig(application, key)
      .map(node -> new ConfigResponse(application, profile, key, node.get(key)));
  }

  private Mono<JsonNode> loadConfig(final String application, final String profile) {
    return webClient.get()
      .uri(String.format("%s-%s.json", application, profile))
      .retrieve()
      .bodyToMono(JsonNode.class);
  }


  @GetMapping("/config/{tenant}/{profile}")
  public Mono<TenantConfigResponse> configByTenant(@PathVariable String tenant,
    @PathVariable String profile) {
    return webClient.get()
      .uri(String.format("%s-%s-%s.json", tenantConfigPrefix, tenant, profile))
      .retrieve()
      .bodyToMono(JsonNode.class)
      .map(node -> new TenantConfigResponse(tenant, profile, node));
  }

}
