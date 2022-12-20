package kodlama.io.Universty.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "student_id", referencedColumnName = "user_id")
@Table(name = "students")
public class Student extends User {
  @Column(name = "student_no")
  private int stundentNo;

  @Column(name = "nationality")
  private String nationality;

  @Column(name = "nationality_id")
  private String nationalityId;

  @Column(name = "birthday")
  private LocalDate birthday;

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;
}
