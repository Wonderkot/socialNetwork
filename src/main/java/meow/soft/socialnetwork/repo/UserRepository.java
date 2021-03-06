package meow.soft.socialnetwork.repo;

import meow.soft.socialnetwork.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends GenericRepository<User> {
    @EntityGraph(value = "User.subscribes")
    Page<User> findAll(Pageable pageable);

    @EntityGraph(value = "User.subscribes")
    Optional<User> findById(UUID id);
}
