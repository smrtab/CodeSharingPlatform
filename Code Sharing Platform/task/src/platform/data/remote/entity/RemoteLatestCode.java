package platform.data.remote.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoteLatestCode {

    @NotBlank
    private String date;

    @NotBlank
    private String code;
}