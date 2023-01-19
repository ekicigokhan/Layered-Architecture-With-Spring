package kodlama.io.Universty.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "departments")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "students"})
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "department_id")
  private int id;

  @Column(name = "department_name")
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "faculty_id")
  @JsonIgnore
  private Faculty faculty;

  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private List<Student> students;

}
