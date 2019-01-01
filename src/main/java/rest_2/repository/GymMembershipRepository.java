package rest_2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import rest_2.entity.GymMembership;

@Component
public interface GymMembershipRepository extends CrudRepository<GymMembership, Long> {
}
