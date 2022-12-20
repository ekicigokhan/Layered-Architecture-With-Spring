package kodlama.io.Universty.dataAccess.abstracts;

import kodlama.io.Universty.entities.concretes.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

  boolean existsByFirstName(String name);

  boolean existsById(int id);

  boolean existsByFirstNameIgnoreCaseAndBranch_Id(String name, int id);
}
