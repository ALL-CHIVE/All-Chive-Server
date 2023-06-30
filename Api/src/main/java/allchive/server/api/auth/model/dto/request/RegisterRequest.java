package allchive.server.api.auth.model.dto.request;


import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty private String email;
    private String phoneNumber;
    private String profileImage;
    @NotEmpty private String name;

    private Boolean marketingAgree = Boolean.FALSE;
}
