package kodlama.io.Universty.webApi.model.requests.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchUpdateRequest {

    private String newBranchName;
}
