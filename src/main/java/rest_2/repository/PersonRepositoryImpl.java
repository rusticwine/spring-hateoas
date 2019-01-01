package rest_2.repository;

import org.springframework.stereotype.Component;
import rest_2.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;

@Component("memoryImpl")
public class PersonRepositoryImpl implements PersonRepository {

    Map<Long, Person> map = new ConcurrentHashMap<>();

    @Override
    public <S extends Person> S save(S s) {
        map.put(s.getId(), s);
        return s;
    }

    @Override
    public <S extends Person> Iterable<S> saveAll(Iterable<S> iterable) {
        StreamSupport.stream(iterable.spliterator(), false).forEach(i -> this.save(i));
        return iterable;
    }

    @Override
    public Optional<Person> findById(Long aLong) {
        return Optional.ofNullable(map.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return map.containsKey(aLong);
    }

    @Override
    public Iterable<Person> findAll() {
        return map.values();
    }

    @Override
    public Iterable<Person> findAllById(Iterable<Long> iterable) {
        List<Person> result = new ArrayList<>();
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
    public void delete(Person person) {
        //TODO - ID may not be present...
        map.remove(person.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends Person> iterable) {
        StreamSupport.stream(iterable.spliterator(), false).forEach(i -> map.remove(((Person) i).getId()));
    }

    @Override
    public void deleteAll() {
        map.clear();
    }
}
