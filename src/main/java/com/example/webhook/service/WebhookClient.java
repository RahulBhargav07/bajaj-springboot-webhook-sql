package com.example.webhook.service;

import com.example.webhook.config.AppProperties;
import com.example.webhook.model.FinalQueryPayload;
import com.example.webhook.model.GenerateWebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookClient {
  private static final Logger log = LoggerFactory.getLogger(WebhookClient.class);
  private final RestTemplate restTemplate = new RestTemplate();
  private final AppProperties props;

  public WebhookClient(AppProperties props) {
    this.props = props;
  }

  public GenerateWebhookResponse generateWebhook() {
    String url = props.getBaseUrl() + props.getGenerateEndpoint();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Object> entity = new HttpEntity<>(
        java.util.Map.of("name", props.getName(), "regNo", props.getRegNo(), "email", props.getEmail()), headers);

    ResponseEntity<GenerateWebhookResponse> resp =
        restTemplate.exchange(url, HttpMethod.POST, entity, GenerateWebhookResponse.class);

    return resp.getBody();
  }

  public String submitFinalQuery(String webhookUrl, String accessToken, String finalQuery) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", accessToken);
    HttpEntity<FinalQueryPayload> entity = new HttpEntity<>(new FinalQueryPayload(finalQuery), headers);

    ResponseEntity<String> resp =
        restTemplate.exchange(webhookUrl, HttpMethod.POST, entity, String.class);

    return resp.getBody();
  }
}
