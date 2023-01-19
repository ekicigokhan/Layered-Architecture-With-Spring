package kodlama.io.Universty.webApi.model.responses.lesson;

import kodlama.io.Universty.entities.concretes.Student;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetAllLessonResponse {
    private int id;
    private String name;
}
