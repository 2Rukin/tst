package controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import model.BaseResponse;
import model.dto.PurchaseRequest;
import model.dto.PurchaseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.purchase.DoPurchaseService;

import java.util.UUID;

import static utility.ApiCodes.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PurchaseController implements PurchaseApi {

    private final DoPurchaseService purchaseService;

    @Override
    @Operation(summary = "Совершить покупку",
            description = "Совершает покупку с учетом налогов и купонов")
    @PostMapping("/purchase")
    public ResponseEntity<BaseResponse<PurchaseResult>> doPurchase(@RequestBody PurchaseRequest request) {
        try {
            PurchaseResult result = purchaseService.doPurchase(request);
            BaseResponse<PurchaseResult> response = BaseResponse.<PurchaseResult>builder()
                    .sessionId(UUID.randomUUID())
                    .status(SUCCESS.getCode())
                    .data(result)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            BaseResponse<PurchaseResult> response = BaseResponse.<PurchaseResult>builder()
                    .sessionId(UUID.randomUUID())
                    .status(ERROR.getCode())
                    .errorDetails(BaseResponse.ErrorDetails.builder()
                            .errorCode(PURCHASE_ERROR.getCode())
                            .errorMessage(e.getMessage())
                            .build())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
