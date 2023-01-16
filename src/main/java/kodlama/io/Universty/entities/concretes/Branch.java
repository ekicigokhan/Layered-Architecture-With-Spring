package kodlama.io.Universty.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "branches")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "teachers"})
public class Branch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "branch_id")
  private int id;

  @Column(name = "branch_name")
  private String name;

  @OneToMany(
      mappedBy = "branch",
      cascade =
          CascadeType
              .ALL) // EAGER : VERİTABANINDAN BRANŞ HER SELECT EDİLDİĞİNİZDE ONUN İLGİLİ TEACHER'IDA
  // GELSİN.
  private List<Teacher> teachers;
}
