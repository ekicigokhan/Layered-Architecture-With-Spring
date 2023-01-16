package kodlama.io.Universty.webApi.model.responses.branch;

import kodlama.io.Universty.entities.concretes.Teacher;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetAllBranchResponse {

    private int branchId;
    private String branchName;

}
