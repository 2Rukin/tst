package model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Data
@Builder
public class BaseRequest<T> {

    @NotNull
    private UUID sessionId;
    @Nullable
    private String authToken;
    @NotNull
    private T data;
}