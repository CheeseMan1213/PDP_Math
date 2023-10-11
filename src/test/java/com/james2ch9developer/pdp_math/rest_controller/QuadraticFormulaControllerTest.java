package com.james2ch9developer.pdp_math.rest_controller;

import com.james2ch9developer.pdp_math.producer.QuadraticFormulaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuadraticFormulaControllerTest {
  @Value("${spring.kafka.topic}")
  private String topic;
   @Autowired
   private MockMvc mockMvc;
   @MockBean
   private QuadraticFormulaProducer producer;
   @Test
   @WithMockUser(username = "james2ch9developer@gmail")
   public void testQuadraticFormula() throws Exception {
     String coefficients = "1, 2, 3";
//     this.mockMvc.perform(post("/quadraticFormula/v1/quadraticFormula")
//       .contentType(MediaType.APPLICATION_JSON)
//       .content(objectMapper.writeValueAsString(coefficients)))
//       .andExpect(status().isOk());
     this.mockMvc.perform(post("/quadraticFormula/v1/quadraticFormula")
         .contentType(MediaType.TEXT_PLAIN)
         .content(coefficients))
         .andExpect(status().isOk());
     verify(producer, times(1)).send(topic, coefficients);
   }
}
