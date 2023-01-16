package kodlama.io.Universty.entities.concretes;

import kodlama.io.Universty.webApi.model.responses.teacher.GetByIdTeacherResponse;
import lombok.*;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

@Table(name = "teachers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "teacher_id", referencedColumnName = "user_id")
public class Teacher extends User {
  @Column(name = "age")
  private int age;

  @Column(name = "salary")
  private double salary;

  @Column(name = "biography")
  private String biography;

  @Column(name = "title")
  private String title;

  @ManyToOne()
  @JoinColumn(name = "branch_id")
  private Branch branch;

  @Builder
  public Teacher(
      int id,
      String firstName,
      String lastName,
      String email,
      String userName,
      String password,
      String gender,
      int age,
      double salary,
      String biography,
      String title,
      Branch branch) {
    super(id, firstName, lastName, email, userName, password, gender);
    this.age = age;
    this.salary = salary;
    this.biography = biography;
    this.title = title;
    this.branch = branch;
  }
}
