package kodlama.io.Universty.webApi.model.responses.teacher;

import kodlama.io.Universty.entities.concretes.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetByIdTeacherResponse {

    private String firstname;
    private String lastName;
    private String branchName;

}
