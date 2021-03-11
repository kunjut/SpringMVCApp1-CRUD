package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    { // блок инициализации
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom", "Motsky", "t.motsky@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", "Obsky", "b.obsky@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", "Ekimsky", "m.ekimsky@com"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", "Ytaksky", "k.ytaksky@com"));
    } // блок инициализации

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
}
