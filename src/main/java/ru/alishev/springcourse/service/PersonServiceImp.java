package ru.alishev.springcourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.dao.PersonDAO;

import java.util.List;

@Service
@Transactional
public class PersonServiceImp implements PersonService {
    @Autowired
    PersonDAO personDAO;

    @Override
    public List<Person> index() {
        return personDAO.index();
    }

    @Override
    public Person show(int id) {
        return personDAO.show(id);
    }

    @Override
    public void save(Person person) {
        personDAO.save(person);
    }

    @Override
    public void update(int id, Person updatedPerson) {
        personDAO.update(id, updatedPerson);
    }

    @Override
    public void delete(int id) {
        personDAO.delete(id);
    }
}
