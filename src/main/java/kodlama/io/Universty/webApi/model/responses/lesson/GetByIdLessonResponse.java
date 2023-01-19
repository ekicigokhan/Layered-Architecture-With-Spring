package kodlama.io.Universty.webApi.model.responses.lesson;

import kodlama.io.Universty.entities.concretes.Student;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetByIdLessonResponse {

    private String name;
    private String description;
    private List<Student> students;
}
