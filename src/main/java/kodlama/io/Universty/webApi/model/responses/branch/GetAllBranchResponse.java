package kodlama.io.Universty.webApi.model.responses.branch;

import kodlama.io.Universty.entities.concretes.Branch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllBranchResponse {

    private int branchId;
    private String branchName;

}
