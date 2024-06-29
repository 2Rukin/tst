package controller;

import model.BaseResponse;
import model.dto.PurchaseRequest;
import model.dto.PurchaseResult;
import org.springframework.http.ResponseEntity;


public interface PurchaseApi {

    ResponseEntity<BaseResponse<PurchaseResult>> doPurchase(PurchaseRequest request);
}