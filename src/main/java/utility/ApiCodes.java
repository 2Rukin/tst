package utility;

public enum ApiCodes {
    SUCCESS("Success"),
    ERROR("Error"),
    CALCULATION_ERROR("Calculation_Error");

    private final String code;

    ApiCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

