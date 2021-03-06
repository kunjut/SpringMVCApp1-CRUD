package ru.alishev.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.models.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDAOImp implements PersonDAO {
    @PersistenceContext
    private EntityManager entityManager;

//    @Autowired
//    private SessionFactory sessionFactory;

    @Override
    public List<Person> index() {
        return entityManager.createQuery(
                "select p from Person p", Person.class)
                .getResultList();

//        Session session = sessionFactory.getCurrentSession();
//        TypedQuery<Person> query = session
//                .createQuery("from Person");
//        return query;

//        так работал без бд на списке
//        return people;
    }

    @Override
    public Person show(int id) {
        TypedQuery<Person> query = entityManager.createQuery("select p from Person p where p.id=:id", Person.class);
        query.setParameter("id", id);
        return query.getSingleResult();

//        Session session = sessionFactory.getCurrentSession();
//        TypedQuery<Person> query = session
//                .createQuery("from Person where id = :id");
//        query.setParameter("id", id);
//        return query.getSingleResult();

//        так работал без бд на списке
//        return people.stream()
//                .filter(person -> person.getId() == id)
//                .findAny().orElse(null);
    }

    @Override
    public void save(Person person) {
        entityManager.persist(person);

//        Session session = sessionFactory.getCurrentSession();
//        session.save(person);

//        так работал без бд на списке
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
    }

    @Override
    public void update(int id, Person updatedPerson) {
        entityManager.merge(updatedPerson);

//        Session session = sessionFactory.getCurrentSession();
//        Person person = show(id);
//        person.setName(updatedPerson.getName());
//        person.setSurname(updatedPerson.getSurname());
//        person.setEmail(updatedPerson.getEmail());
//        session.save(person);

//        так работал без бд на списке
//        // получили человека, которого надо обновить
//        Person personToBeUpdated = show(id);
//        // обновляем полученными с формы данными
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setSurname(updatedPerson.getSurname());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    @Override
    public void delete(int id) {
        entityManager.remove(show(id));

//        Person person = new Person();
//        // hibernate deletes objects by the primary key
//        person.setId(id);
//        Session session = sessionFactory.getCurrentSession();
//        session.delete(person);

//        так работал без бд на списке
//        people.removeIf(person -> person.getId() == id);
    }
}
