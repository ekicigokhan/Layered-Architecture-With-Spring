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
public class BranchUpdateRequest {

    @NotNull
    @NotBlank
    private String name;
}
