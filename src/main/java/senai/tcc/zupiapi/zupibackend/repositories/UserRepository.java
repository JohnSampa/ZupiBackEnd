package senai.tcc.zupiapi.zupibackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.tcc.zupiapi.zupibackend.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
