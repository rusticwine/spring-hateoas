package rest_2.repository;

import org.springframework.data.repository.CrudRepository;
import rest_2.entity.GymMembership;

public interface GymMembershipRepository extends CrudRepository<GymMembership, Long> {
}
