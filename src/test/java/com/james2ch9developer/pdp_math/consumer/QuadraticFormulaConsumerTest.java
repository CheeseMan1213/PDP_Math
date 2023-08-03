package com.james2ch9developer.pdp_math.consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

import com.james2ch9developer.pdp_math.comsumer.QuadraticFormulaConsumer;
import com.james2ch9developer.pdp_math.document.QuadraticFormula;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class QuadraticFormulaConsumerTest {

  private Logger logger = (Logger) LoggerFactory.getLogger(QuadraticFormulaConsumerTest.class);

  @Autowired
  private KafkaTemplate<String, String> template;

  @Autowired
  private QuadraticFormulaConsumer consumer;

  @Value("${spring.kafka.topic}")
  private String topic;

  @Test
  public void consumerMessageReceivedTest() {
    logger.info("consumerMessageReceivedTest()");

    String quadraticFormulaArguments = "1, 4, 3, john.doe@yahoo.com";
    // DeleteMe: This is just to test the spotbugs error: NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE
    // String name = null;
    // System.out.println(name.length());

    template.send(topic, quadraticFormulaArguments);

    // This Thread.sleep() needs to be here. I was getting the error: IllegalArgumentException
    // And it was coming from the one that I throw in my if statement from QuadraticFormula.java
    // I sermised that there was not enough time between the production of the message in the
    // consumption of the message. I was right, because this Thread.sleep() fixed it.
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    QuadraticFormula quadraticFormulaData = new QuadraticFormula.Builder()
        .setCoefficientsAndUserEmail(consumer.getMessage()).build();

    assertEquals(1, quadraticFormulaData.a());
    assertEquals(4, quadraticFormulaData.b());
    assertEquals(3, quadraticFormulaData.c());
    assertEquals("john.doe@yahoo.com", quadraticFormulaData.userEmail());
    assertEquals(-1.0, quadraticFormulaData.answer1());
    assertEquals(-3.0, quadraticFormulaData.answer2());
  }

}
