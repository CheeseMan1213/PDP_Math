//package com.james2ch9developer.pdp_math.producer;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.kafka.core.KafkaTemplate;
//
//import com.james2ch9developer.pdp_math.document.QuadraticFormula;
//import org.springframework.util.concurrent.SettableListenableFuture;
//
//import java.util.concurrent.ExecutionException;
//
//@ExtendWith(MockitoExtension.class)
//public class QuadraticFormulaProducerTest {
//
//  @Mock
//  private KafkaTemplate<String, QuadraticFormula> kafkaTemplate;
//
//  @InjectMocks
//  private QuadraticFormulaProducer quadraticFormulaProducer;
//
////  @Test
////  void testQuadraticFormulaProducerSuccess() {
////    // Given
////    final String[] coefficientsAndUserEmail = {"1", "4", "3", "john.doe@yahoo.com"};
////    QuadraticFormula quadraticFormula = new QuadraticFormula.Builder()
////      .setCoefficientsAndUserEmail(coefficientsAndUserEmail).build();
////    // When
////    quadraticFormulaProducer.send("quadratic-formula-topic", String.valueOf(quadraticFormula));
////    // Then
////    verify(kafkaTemplate).send(any(String.class), any(QuadraticFormula.class));
////  }
//  @Test
//  void testQuadraticFormulaProducerSuccess() {
//    //given
//    final String[] coefficientsAndUserEmail = {"1", "4", "3", "john.doe@yahoo.com"};
//    QuadraticFormula quadraticFormula = new QuadraticFormula.Builder()
//      .setCoefficientsAndUserEmail(coefficientsAndUserEmail).build();
//    SettableListenableFuture future = new SettableListenableFuture();
//
//    ProducerRecord<Integer, String> producerRecord = new ProducerRecord("quadratic-formula-topic", quadraticFormula);
//
//    //when
//    quadraticFormulaProducer.send("quadratic-formula-topic", String.valueOf(producerRecord));
//    //then
//
//  }
//}
