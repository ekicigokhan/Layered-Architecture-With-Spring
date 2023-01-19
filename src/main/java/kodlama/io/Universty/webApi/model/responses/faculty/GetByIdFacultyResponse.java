package kodlama.io.Universty.webApi.model.responses.faculty;

import kodlama.io.Universty.entities.concretes.Department;
import kodlama.io.Universty.entities.concretes.Student;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetByIdFacultyResponse {
    private String name;
    private List<Department> departments;
}
