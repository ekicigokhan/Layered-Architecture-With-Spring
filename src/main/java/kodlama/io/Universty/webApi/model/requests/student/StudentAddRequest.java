package kodlama.io.Universty.webApi.model.requests.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAddRequest {

    @NotNull
    @Positive
    private int departmentId;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    @Positive
    private int studentNo;

    @NotNull
    @NotBlank
    private String nationality;

    @NotNull
    @NotBlank
    private String nationalIdentity;

    @NotNull
    @NotBlank
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

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
    @Size(min = 4, max = 12)
    private String password;
}
