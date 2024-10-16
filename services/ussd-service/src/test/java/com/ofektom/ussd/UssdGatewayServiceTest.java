package com.ofektom.ussd;

import com.ofektom.ussd.dto.UssdRequest;
import com.ofektom.ussd.dto.UssdResponse;
import com.ofektom.ussd.entity.UssdSession;
import com.ofektom.ussd.repository.UssdSessionRepository;
import com.ofektom.ussd.service.impl.UssdGatewayServiceImpl;
import com.ofektom.ussd.service.UssdGatewayService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class UssdGatewayServiceTest {

    @MockBean
    private UssdSessionRepository sessionRepository;

    @MockBean
    private UssdGatewayService operatorAGateway;

    @InjectMocks
    private UssdGatewayServiceImpl ussdGatewayService;

    @Test
    void testProcessUssdRequest() {
        // Set up mock behavior
        UssdRequest request = new UssdRequest("sessionId123", "1234567890", "OperatorA", "1234","Hello");

        UssdSession session = new UssdSession();
        session.setSessionId("sessionId123");
        session.setPhoneNumber("1234567890");

        when(sessionRepository.findBySessionId(request.sessionId())).thenReturn(Optional.of(session));
        when(operatorAGateway.handleRequest(request)).thenReturn(new UssdResponse("sessionId123", "Response from Operator A"));

        UssdResponse response = ussdGatewayService.processUssdRequest(request, "ry867566f6776");

        assertNotNull(response);
        assertEquals("Response from Operator A", response.getResponseMessage());
    }
}

