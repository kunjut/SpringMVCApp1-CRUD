package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        // получим всех людей из DAO и передадим во вьюху
        model.addAttribute("people", personDAO.index());

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // получим одного человека по id из DAO и передадим во вьюху
        model.addAttribute("person", personDAO.show(id));
        
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Person person) {

        return "people/new";
    }

    @PostMapping()
    public String create(Person person) {
        // добавляем person в хранилище через DAO
        personDAO.save(person);

        return "redirect:/people";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        // пакуем человека по id в модель, чтобы поля формы были заполнены
        model.addAttribute("person", personDAO.show(id));

        return "people/edit";
    }
}
