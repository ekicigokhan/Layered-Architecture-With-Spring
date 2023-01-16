package kodlama.io.Universty.webApi.model.requests.teacher;

import lombok.*;

import jakarta.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TeacherAddRequest {

    @Positive
    private int branchId;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
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
    @Email(message = "E-POSTA GEÇERLİ OLMALIDIR !")
    private String email;

    @NotNull
    @NotBlank
    @Size( min = 4,max = 22 ,message = "BU ALAN BOŞ BIRAKILAMAZ !")
    private String userName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,15}$")
   /* @Size(min = 4, max = 12)*/
    private String password;

}
