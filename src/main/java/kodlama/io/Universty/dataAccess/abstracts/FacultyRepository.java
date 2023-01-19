package kodlama.io.Universty.dataAccess.abstracts;

import kodlama.io.Universty.entities.concretes.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    boolean existsById(int id);

    boolean existsByName(String name);
}
