package kodlama.io.Universty.webApi.model.requests.branch;

import kodlama.io.Universty.business.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.*;
import org.springframework.context.MessageSource;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchAddRequest {

    @NotBlank(message = Messages.RequestMessages.ADD_DATA_PLEASE)
    private String name;
}
