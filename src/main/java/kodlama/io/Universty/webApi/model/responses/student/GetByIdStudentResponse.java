package kodlama.io.Universty.webApi.model.responses.student;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetByIdStudentResponse {

    private String firstName;
    private String lastName;
    private String departmentName;
    private int studentNo;
    private String nationality;
    private LocalDate birthday;
    private String gender;
    private String email;
}
