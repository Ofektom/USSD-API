package com.ofektom.ussd.controller;


import com.ofektom.ussd.dto.UssdRequest;
import com.ofektom.ussd.dto.UssdResponse;
import com.ofektom.ussd.service.OperatorManagementService;
import com.ofektom.ussd.utils.OperatorManagementFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/operators")
@RequiredArgsConstructor
public class OperatorController {

    private final OperatorManagementFactory operatorManagementFactory;

    @PostMapping("/ussd")
    public ResponseEntity<UssdResponse> handleUssdRequest(
            @RequestBody UssdRequest ussdRequest) {

        // Delegate the request to the appropriate service based on the operator code
        OperatorManagementService operatorService = operatorManagementFactory.getOperatorService(ussdRequest.operator());
        if (operatorService == null) {
            return ResponseEntity.badRequest().body(new UssdResponse(null,"Unknown operator"));
        }

        UssdResponse response = operatorService.handleRequest(ussdRequest);
        return ResponseEntity.ok(response);
    }
}
