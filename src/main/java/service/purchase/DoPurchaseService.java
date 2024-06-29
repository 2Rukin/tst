package service.purchase;

import model.dto.PurchaseRequest;
import model.dto.PurchaseResult;

public interface DoPurchaseService {

    PurchaseResult doPurchase(PurchaseRequest request);
}
