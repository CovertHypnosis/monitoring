package ge.jemali.monitoring.repositories;

import ge.jemali.monitoring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}