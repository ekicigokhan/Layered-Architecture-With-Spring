package kodlama.io.Universty.webApi.model.requests.department;

import kodlama.io.Universty.business.constants.Messages;
import kodlama.io.Universty.entities.concretes.Faculty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentAddRequest {

    private int facultyId;

    @NotBlank(message = Messages.RequestMessages.ADD_DATA_PLEASE)
    private String name;
    private String description;

}
