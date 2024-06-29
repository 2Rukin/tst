package model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class PurchaseResult {

    private boolean isSuccessPurchase;
    @Nullable
    private String message;
}
