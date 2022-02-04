package platform.data.remote.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoteCode {

    @NotBlank
    private String date;

    @NotBlank
    private String code;

    private Long time;

    private Long views;

    @JsonIgnore
    private boolean isTimeRestricted;

    @JsonIgnore
    private boolean isViewsRestricted;
}