package com.ofektom.ussd.service.impl;

import com.ofektom.ussd.dto.UssdRequest;
import com.ofektom.ussd.dto.UssdResponse;
import com.ofektom.ussd.service.OperatorManagementService;
import org.jsmpp.bean.*;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.jsmpp.session.SubmitSmResult;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AirtelGateway implements OperatorManagementService {
    private final SMPPSession smppSession;

    public AirtelGateway() throws IOException {
        smppSession = new SMPPSession();
        smppSession.connectAndBind("airtel.smpp.gateway", 2775,
                new BindParameter(BindType.BIND_TRX, "username", "password", null, TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, null));
    }

    @Override
    public String getOperatorCode() {
        return "Airtel";
    }

    @Override
    public UssdResponse handleRequest(UssdRequest request) {
        try {
            SubmitSmResult result = smppSession.submitShortMessage("cm",
                    null, null, request.phoneNumber(),
                    null, null, "shortcode",
                    null, (byte) 0, (byte) 1, null, null,
                    new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT), (byte) 0,
                    new GeneralDataCoding(Alphabet.ALPHA_DEFAULT), (byte) 0,
                    request.message().getBytes());

            return new UssdResponse(result.getMessageId(), "Airtel USSD message sent");
        } catch (Exception e) {
            throw new RuntimeException("Error sending Airtel USSD message", e);
        }
    }
}
