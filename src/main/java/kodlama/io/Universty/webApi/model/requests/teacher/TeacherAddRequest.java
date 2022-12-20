package kodlama.io.Universty.webApi.model.requests.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherAddRequest {

    private int branchId;
    private String firstName;
    private String lastName;
    private int age;
    private String biography;
    private String title;
    private int salary;
    private String userName;
    private String password;
    private String gender;
    private String email;
}
