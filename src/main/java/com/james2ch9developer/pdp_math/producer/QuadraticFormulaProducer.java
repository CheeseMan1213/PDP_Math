package com.james2ch9developer.pdp_math.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class QuadraticFormulaProducer {

  private static final Logger LOGGER = LoggerFactory.getLogger(QuadraticFormulaProducer.class);

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public final void send(final String topic, final String payload) {
    LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
    kafkaTemplate.send(topic, payload);
  }
}
