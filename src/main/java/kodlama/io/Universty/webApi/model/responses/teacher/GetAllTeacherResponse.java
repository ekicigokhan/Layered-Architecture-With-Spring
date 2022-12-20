package kodlama.io.Universty.webApi.model.responses.teacher;

import kodlama.io.Universty.entities.concretes.Branch;
import kodlama.io.Universty.entities.concretes.Teacher;
import kodlama.io.Universty.webApi.model.responses.branch.GetAllBranchResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetAllTeacherResponse {

    private int id;
    private String firstName;
    private String lastName;
    private Branch branch;

}
