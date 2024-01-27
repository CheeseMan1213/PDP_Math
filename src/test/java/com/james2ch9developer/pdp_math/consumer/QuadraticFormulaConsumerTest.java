package com.james2ch9developer.pdp_math.consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.james2ch9developer.pdp_math.document.QuadraticFormula;
import com.james2ch9developer.pdp_math.service.QuadraticFormulaService;

@ExtendWith(MockitoExtension.class)
public class QuadraticFormulaConsumerTest {

    @Mock
    private QuadraticFormulaService quadraticFormulaService;

    @InjectMocks
    private QuadraticFormulaConsumer quadraticFormulaConsumer;

    @Test
    void testQuadraticFormulaConsumerSuccess() {
        // Given
        ConsumerRecord<String, String> consumerRecord =
          new ConsumerRecord<>("quadratic-formula-topic", 0, 0, "1", "1, 4, 3, john.doe@yahoo.com");
        // When
        quadraticFormulaConsumer.receive(consumerRecord);
        // Then
        verify(quadraticFormulaService).save(any(QuadraticFormula.class));
    }
}
