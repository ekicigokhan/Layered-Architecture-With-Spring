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
    private String name;
}
