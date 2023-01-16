package kodlama.io.Universty.webApi.model.responses.teacher;

import kodlama.io.Universty.entities.concretes.Branch;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GetAllTeacherResponse {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String biography;
    private String title;
    private double salary;
    private String gender;
    private String email;
    private String userName;
    private String password;
    private Branch branch;

}
