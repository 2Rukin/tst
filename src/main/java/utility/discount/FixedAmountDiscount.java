package utility.discount;

import java.math.BigDecimal;

public record FixedAmountDiscount(BigDecimal discountAmount) implements DiscountTypes {

    @Override
    public BigDecimal applyDiscount(BigDecimal price) {
        return price.subtract(discountAmount);
    }
}
