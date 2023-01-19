package kodlama.io.Universty.webApi.model.responses.faculty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetAllFacultyResponse {
    private int id;
    private String name;
}
