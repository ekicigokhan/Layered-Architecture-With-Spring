package kodlama.io.Universty.webApi.model.requests.lesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LessonUpdateRequest {

    private String name;
    private String description;
}
