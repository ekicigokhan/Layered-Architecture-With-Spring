package kodlama.io.Universty.dataAccess.abstracts;

import kodlama.io.Universty.entities.concretes.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    boolean existsById(int id);

    boolean existsByName(String name);
}
