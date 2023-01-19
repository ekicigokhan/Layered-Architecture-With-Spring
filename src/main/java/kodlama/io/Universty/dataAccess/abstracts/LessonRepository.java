package kodlama.io.Universty.dataAccess.abstracts;

import kodlama.io.Universty.entities.concretes.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {

    boolean existsById(int id);

    boolean existsByName(String name);
}
