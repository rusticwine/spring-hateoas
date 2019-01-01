package rest_2.repository;

import org.springframework.stereotype.Component;
import rest_2.entity.GymMembership;
import rest_2.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;

@Component("memoryImplGym")
public class GymMembershipRepositoryImpl implements GymMembershipRepository {

    Map<Long, GymMembership> map = new ConcurrentHashMap<>();

    @Override
    public <S extends GymMembership> S save(S s) {
        map.put(s.getId(), s);
        return s;
    }

    @Override
    public <S extends GymMembership> Iterable<S> saveAll(Iterable<S> iterable) {
        StreamSupport.stream(iterable.spliterator(), false).forEach(i -> this.save(i));
        return iterable;
    }

    @Override
    public Optional<GymMembership> findById(Long aLong) {
        return Optional.ofNullable(map.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return map.containsKey(aLong);
    }

    @Override
    public Iterable<GymMembership> findAll() {
        return map.values();
    }

    @Override
    public Iterable<GymMembership> findAllById(Iterable<Long> iterable) {
        List<GymMembership> result = new ArrayList<>();
        StreamSupport.stream(iterable.spliterator(), false).forEach(i -> result.add(map.get(i)));
        return result;
    }

    @Override
    public long count() {
        return map.size();
    }

    @Override
    public void deleteById(Long aLong) {
        map.remove(aLong);
    }

    @Override
    public void delete(GymMembership person) {
        //TODO - ID may not be present...
        map.remove(person.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends GymMembership> iterable) {
        StreamSupport.stream(iterable.spliterator(), false).forEach(i -> map.remove(((GymMembership) i).getId()));
    }

    @Override
    public void deleteAll() {
        map.clear();
    }
}
