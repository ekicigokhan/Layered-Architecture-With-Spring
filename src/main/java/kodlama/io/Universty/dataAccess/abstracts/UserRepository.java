package kodlama.io.Universty.dataAccess.abstracts;

import kodlama.io.Universty.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
