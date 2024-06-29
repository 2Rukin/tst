package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class TaxNumberValidator implements ConstraintValidator<ValidTaxNumber, String> {

    private static final Pattern GERMAN_TAX_NUMBER_PATTERN = Pattern.compile("DE[0-9]{9}");
    private static final Pattern ITALIAN_TAX_NUMBER_PATTERN = Pattern.compile("IT[0-9]{11}");
    private static final Pattern GREEK_TAX_NUMBER_PATTERN = Pattern.compile("GR[0-9]{9}");
    private static final Pattern FRENCH_TAX_NUMBER_PATTERN = Pattern.compile("FR[A-Z]{2}[0-9]{9}");

    @Override
    public void initialize(ValidTaxNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String taxNumber, ConstraintValidatorContext context) {
        if (taxNumber == null) {
            return false;
        }
        return GERMAN_TAX_NUMBER_PATTERN.matcher(taxNumber).matches() ||
                ITALIAN_TAX_NUMBER_PATTERN.matcher(taxNumber).matches() ||
                GREEK_TAX_NUMBER_PATTERN.matcher(taxNumber).matches() ||
                FRENCH_TAX_NUMBER_PATTERN.matcher(taxNumber).matches();
    }
}