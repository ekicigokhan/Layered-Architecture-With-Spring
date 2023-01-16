package kodlama.io.Universty.webApi.model.requests.department;

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

    @NotBlank(message = "LÜTFEN BİR BÖLÜM ADI GİRİNİZ !")
    private String name;
    private String description;
}
