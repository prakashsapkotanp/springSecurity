package bloodlink.application.bloodlink.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bloodlink.application.bloodlink.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User>findByEmail(String Email);
}
