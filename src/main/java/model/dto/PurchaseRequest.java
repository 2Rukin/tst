package model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Builder
public class PurchaseRequest extends PriceCalculationRequest {

    @NotBlank
    private String paymentProcessorType;
}
