package com.example.webhook.service;

import com.example.webhook.model.GenerateWebhookResponse;
import com.example.webhook.config.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
  private static final Logger log = LoggerFactory.getLogger(Runner.class);

  private final AppProperties props;
  private final WebhookClient client;
  private final SqlSolver solver;

  public Runner(AppProperties props, WebhookClient client, SqlSolver solver) {
    this.props = props;
    this.client = client;
    this.solver = solver;
  }

  @Override
  public void run(String... args) {
    try {
      GenerateWebhookResponse response = client.generateWebhook();
      String finalSql = solver.computeFinalSql(props.getRegNo());
      String submissionResponse = client.submitFinalQuery(response.getWebhook(), response.getAccessToken(), finalSql);
      log.info("Server response: {}", submissionResponse);
    } catch (Exception e) {
      log.error("Error running workflow: {}", e.getMessage(), e);
    }
  }
}
