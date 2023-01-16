package kodlama.io.Universty.webApi.model.responses.teacher;

import kodlama.io.Universty.entities.concretes.Teacher;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GetByIdTeacherResponse {

    private String firstName;
    private String lastName;
    private String branchName;
    private int age;
    private String title;
    private String biography;
    private double salary;

}
