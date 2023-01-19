package kodlama.io.Universty.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "student_id", referencedColumnName = "user_id")
@Table(name = "students")
public class Student extends User {
  @Column(name = "student_no")
  private int studentNo;

  @Column(name = "nationality")
  private String nationality;

  @Column(name = "national_Identity")
  private String nationalIdentity;

  @Column(name = "birthday")
  private LocalDate birthday;

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;
  @ManyToMany
  @JsonIgnore
  private List<Lesson> lessons;

  @Builder
  public Student(
      int id,
      String firstName,
      String lastName,
      String email,
      String userName,
      String password,
      String gender,
      int studentNo,
      String nationality,
      String nationalIdentity,
      LocalDate birthday,
      Department department,List<Lesson> lessons) {
    super(id, firstName, lastName, email, userName, password, gender);
    this.studentNo = studentNo;
    this.nationality = nationality;
    this.nationalIdentity = nationalIdentity;
    this.birthday = birthday;
    this.department = department;
    this.lessons = lessons;
  }
}
