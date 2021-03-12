package ru.alishev.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.models.Person;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
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

    @Autowired
    private SessionFactory sessionFactory;
//
//    Session session = sessionFactory.getCurrentSession();


    public List<Person> index() {
        TypedQuery<Person> query=sessionFactory.getCurrentSession().createQuery("from Person");
        return query.getResultList();
//        return people;
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

    public void update(int id, Person updatedPerson) {
        // получили человека, которого надо обновить
        Person personToBeUpdated = show(id);
        // обновляем полученными с формы данными
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setSurname(updatedPerson.getSurname());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
