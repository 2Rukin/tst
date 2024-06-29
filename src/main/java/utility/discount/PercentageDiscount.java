package utility.discount;

import java.math.BigDecimal;

public record PercentageDiscount(BigDecimal discountPercentage) implements DiscountTypes {

    private static final int HUNDRED = 100;

    @Override
    public BigDecimal applyDiscount(BigDecimal price) {
        BigDecimal discount = price
                .multiply(discountPercentage)
                .divide(BigDecimal.valueOf(HUNDRED));
        return price.subtract(discount);
    }
}