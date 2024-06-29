package controller;

import model.BaseResponse;
import model.dto.PriceCalculationRequest;
import model.dto.PriceCalculationResult;
import org.springframework.http.ResponseEntity;


public interface PriceCalculatorApi {

    ResponseEntity<BaseResponse<PriceCalculationResult>> calculatePrice(PriceCalculationRequest request);
}