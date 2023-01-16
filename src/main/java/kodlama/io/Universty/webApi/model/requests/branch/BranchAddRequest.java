package kodlama.io.Universty.webApi.model.requests.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchAddRequest {

    @NotBlank(message = "LÜTFEN BİR BRANŞ ADI GİRİNİZ !")
    private String name;
}
