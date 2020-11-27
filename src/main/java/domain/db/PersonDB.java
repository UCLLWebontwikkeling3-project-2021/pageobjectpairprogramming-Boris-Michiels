package domain.db;

import domain.model.Person;

import java.util.List;

public interface PersonDB {
    void add(Person person);
    void update(Person person);
    List<Person> getAll();
    Person get(String userid);
    void delete(Person person);
}