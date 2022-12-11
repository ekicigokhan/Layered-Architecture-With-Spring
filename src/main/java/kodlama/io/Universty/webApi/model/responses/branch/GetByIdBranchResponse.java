package kodlama.io.Universty.webApi.model.responses.branch;

import kodlama.io.Universty.entities.concretes.Branch;
import kodlama.io.Universty.entities.concretes.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetByIdBranchResponse {

    private String branchName;
}
