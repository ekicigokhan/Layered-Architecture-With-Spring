package kodlama.io.Universty.webApi.model.responses.student;

import kodlama.io.Universty.entities.concretes.Department;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllStudentResponse {

  private int id;
  private String firstName;
  private String lastName;
  private int studentNo;
  private String nationality;
  private String nationalIdentity;
  private LocalDate birthday;
  private String gender;
  private String email;
  private String userName;
  private String password;
  private Department department;
}
