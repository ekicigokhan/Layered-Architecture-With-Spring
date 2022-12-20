package kodlama.io.Universty.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
}
