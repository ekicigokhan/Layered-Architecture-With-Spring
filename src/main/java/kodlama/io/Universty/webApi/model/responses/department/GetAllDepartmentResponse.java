package kodlama.io.Universty.webApi.model.responses.department;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllDepartmentResponse {

    private int departmentId;
    private String departmentName;
    private String description;
}
