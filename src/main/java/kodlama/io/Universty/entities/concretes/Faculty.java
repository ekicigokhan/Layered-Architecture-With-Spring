package kodlama.io.Universty.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "faculties")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "departments"})
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private int id;

    @Column(name = "faculty_name")
    private String name;
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<Department> departments;
}
