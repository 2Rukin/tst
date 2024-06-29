package service.price_calc;

import model.dto.PriceCalculationRequest;
import model.dto.PriceCalculationResult;

public interface PriceCalculatorService {

    PriceCalculationResult calculateProductPrice (PriceCalculationRequest request);
}
