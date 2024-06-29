package service.tax_calculate;

import java.math.BigDecimal;

public interface TaxService {

    BigDecimal calculateTax(String taxNumber, BigDecimal price);
}
