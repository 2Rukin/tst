package service;

import model.dto.PriceCalculationRequest;
import model.dto.PriceCalculationResult;
import org.springframework.stereotype.Service;

@Service
public class PriceCalculatorServiceImpl implements PriceCalculatorService {

    @Override
    public PriceCalculationResult calculateProductPrice(PriceCalculationRequest request) {
        return PriceCalculationResult.builder()
                .price(186.3)
                .build();
    }
}
