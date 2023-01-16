package kodlama.io.Universty.dataAccess.abstracts;

import kodlama.io.Universty.entities.concretes.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    boolean existsByFirstName(String name);

    boolean existsById(int id);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByFirstNameAndLastNameIgnoreCaseAndDepartment_Id(String firstName,String lastName, int id);
}
