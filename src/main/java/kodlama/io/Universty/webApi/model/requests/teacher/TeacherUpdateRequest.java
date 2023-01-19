package kodlama.io.Universty.webApi.model.requests.teacher;

import kodlama.io.Universty.business.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherUpdateRequest {

    @Positive
    private int branchId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Positive
    @Min(22)@Max(55)
    private int age;

    @NotNull
    private String biography;

    @NotNull
    @NotBlank
    private String title;

    @Positive
    private double salary;

    @NotNull
    @NotBlank
    private String gender;

    @NotNull
    @NotBlank
    @Email(message = Messages.RequestMessages.ADD_VALİD_EMAİL_PLEASE)
    private String email;

    @NotNull
    @NotBlank
    @Size( min = 4,max = 22 ,message = Messages.ErrorMessages.CANNOT_BE_BLANK)
    private String userName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,15}$")
    @Size(min = 4, max = 12)
    private String password;
}
