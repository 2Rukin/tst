package controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import model.BaseResponse;
import model.dto.PriceCalculationRequest;
import model.dto.PriceCalculationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.price_calc.PriceCalculatorService;

import java.util.UUID;

import static utility.ApiCodes.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PriceCalculatorController implements PriceCalculatorApi {

    private final PriceCalculatorService priceCalculatorService;


    @Operation(summary = "Рассчитать цену продукта",
            description = "Рассчитывает цену продукта с учетом налогов и купонов")
    @PostMapping("/calculate-price")
    public ResponseEntity<BaseResponse<PriceCalculationResult>> calculatePrice(@RequestBody PriceCalculationRequest request) {
        try {
            PriceCalculationResult result = priceCalculatorService.calculateProductPrice(request);
            BaseResponse<PriceCalculationResult> response = BaseResponse.<PriceCalculationResult>builder()
                    .sessionId(UUID.randomUUID())
                    .data(result)
                    .status(SUCCESS.getCode())
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            BaseResponse<PriceCalculationResult> response = BaseResponse.<PriceCalculationResult>builder()
                    .sessionId(UUID.randomUUID())
                    .status(ERROR.getCode())
                    .errorDetails(BaseResponse.ErrorDetails.builder()
                            .errorCode(CALCULATION_ERROR.getCode())
                            .errorMessage(e.getMessage())
                            .build())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}