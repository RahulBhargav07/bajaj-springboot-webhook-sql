package com.example.webhook.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
  private String name;
  private String regNo;
  private String email;
  private String baseUrl;
  private String generateEndpoint;
  private String defaultSubmitEndpoint;

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getRegNo() { return regNo; }
  public void setRegNo(String regNo) { this.regNo = regNo; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getBaseUrl() { return baseUrl; }
  public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }
  public String getGenerateEndpoint() { return generateEndpoint; }
  public void setGenerateEndpoint(String generateEndpoint) { this.generateEndpoint = generateEndpoint; }
  public String getDefaultSubmitEndpoint() { return defaultSubmitEndpoint; }
  public void setDefaultSubmitEndpoint(String defaultSubmitEndpoint) { this.defaultSubmitEndpoint = defaultSubmitEndpoint; }
}
