package com.james2ch9developer.pdp_math.consumer;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import com.james2ch9developer.pdp_math.document.QuadraticFormula;
import com.james2ch9developer.pdp_math.service.QuadraticFormulaService;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

@Component
public class QuadraticFormulaConsumer {

  private final Logger logger = (Logger) LoggerFactory.getLogger(QuadraticFormulaConsumer.class);
  private String[] message;
  @Autowired
  private QuadraticFormulaService service;
  private CountDownLatch latch = new CountDownLatch(1);

  @KafkaListener(topics = "${spring.kafka.topic}")
  public final void receive(final ConsumerRecord<String, String> consumerRecord) {
    logger.info("received message='{}'", consumerRecord.toString());
    message = consumerRecord.value().split(", ");
    logger.info("message was: " + Arrays.toString(message));
    latch.countDown();

    QuadraticFormula quadraticFormulaData = new QuadraticFormula.Builder()
      .setCoefficientsAndUserEmail(message).build();
    service.save(quadraticFormulaData);

    // DeleteMe: This is just to test the spotbugs error: NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE
    // String name = null;
    // System.out.println(name.length());
  }

  public final String[] getMessage() {
    // I return a copy here because of the spotbugs error: EI_EXPOSE_REP: May expose
    // internal representation by returning reference to mutable object
    return Arrays.copyOf(message, 4);
  }

  public final CountDownLatch getLatch() {
    return latch;
  }

  public final void resetLatch() {
    latch = new CountDownLatch(1);
  }
}
