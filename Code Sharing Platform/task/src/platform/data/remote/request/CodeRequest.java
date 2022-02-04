package platform.data.remote.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
public class CodeRequest {
    private String code;
    private Long views;
    private Long time;
}
