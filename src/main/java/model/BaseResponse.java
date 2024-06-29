package model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Data
@Builder
public class BaseResponse <T> {

    @NotNull
    private UUID sessionId;
    @NotNull
    private String status;
    @Nullable
    private ErrorDetails errorDetails;
    @Nullable
    private T data;

    @Builder
    public record ErrorDetails(String errorCode,
                               String errorMessage) {
    }
}
