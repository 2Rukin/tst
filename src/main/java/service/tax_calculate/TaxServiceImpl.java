package service.tax_calculate;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import validation.ValidTaxNumber;

import java.math.BigDecimal;
import java.util.regex.Pattern;

@Service
@Validated
public class TaxServiceImpl implements TaxService {

    @Override
    public BigDecimal calculateTax(@ValidTaxNumber String taxNumber, BigDecimal price) {
        BigDecimal taxRate = getTaxRate(taxNumber);
        return price.add(price.multiply(taxRate).divide(BigDecimal.valueOf(100)));
    }

    private BigDecimal getTaxRate(String taxNumber) {
        if (Pattern.matches("DE\\d{9}", taxNumber)) {
            return BigDecimal.valueOf(19);
        } else if (Pattern.matches("IT\\d{11}", taxNumber)) {
            return BigDecimal.valueOf(22);
        } else if (Pattern.matches("FR[A-Z]{2}\\d{9}", taxNumber)) {
            return BigDecimal.valueOf(20);
        } else if (Pattern.matches("GR\\d{9}", taxNumber)) {
            return BigDecimal.valueOf(24);
        } else {
            throw new IllegalArgumentException("Invalid tax number format");
        }
    }
}

