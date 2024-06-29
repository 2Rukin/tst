package model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;


@Data
@Builder
public class PriceCalculationRequest {

    @NotNull
    private Long productId;
    @NotNull
    private String taxNumber;
    @Nullable
    private String couponCode;
}
