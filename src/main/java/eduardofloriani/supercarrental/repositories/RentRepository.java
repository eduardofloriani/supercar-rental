package eduardofloriani.supercarrental.repositories;

import eduardofloriani.supercarrental.models.RentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentRepository extends JpaRepository<RentModel, UUID> {
}
