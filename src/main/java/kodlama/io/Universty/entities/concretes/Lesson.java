package kodlama.io.Universty.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "lessons")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "students"})
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private int id;
    @Column(name = "lesson_name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Teacher> teachers;
    @ManyToMany(mappedBy = "lessons")
    private List<Student> students;

}
