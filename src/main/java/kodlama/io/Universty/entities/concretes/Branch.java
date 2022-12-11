package kodlama.io.Universty.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "branches")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "teachers"})
public class Branch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "branch_id")
  private int id;

  @Column(name = "branch_name", length = 50, nullable = false)
  private String name;

  @OneToMany(mappedBy = "branch")
  private List<Teacher> teachers;
}
