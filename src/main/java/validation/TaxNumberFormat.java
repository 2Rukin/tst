package validation;

public enum TaxNumberFormat {
    GERMANY("DE", "DE[0-9]{9}"),
    ITALY("IT", "IT[0-9]{11}"),
    GREECE("GR", "GR[0-9]{9}"),
    FRANCE("FR", "FR[A-Z]{2}[0-9]{9}");

    private final String countryCode;
    private final String regex;

    TaxNumberFormat(String countryCode, String regex) {
        this.countryCode = countryCode;
        this.regex = regex;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getRegex() {
        return regex;
    }

    public static boolean isValid(String taxNumber) {
        for (TaxNumberFormat format : values()) {
            if (taxNumber.matches(format.getRegex())) {
                return true;
            }
        }
        return false;
    }
}