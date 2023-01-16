package kodlama.io.Universty.webApi.model.responses.department;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetByIdDepartmentResponse {

    private String departmentName;
    private String description;
}
