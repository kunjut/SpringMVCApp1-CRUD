package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.service.PersonService;
import ru.alishev.springcourse.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        // получим всех людей из DAO и передадим во вьюху
        model.addAttribute("people", personService.index());

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // получим одного человека по id из DAO и передадим во вьюху
        model.addAttribute("person", personService.show(id));
        
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Person person) {

        return "people/new";
    }

    @PostMapping()
    public String create(Person person) {
        // добавляем person в хранилище через DAO
        personService.save(person);

        return "redirect:/people";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        // пакуем человека по id в модель, чтобы поля формы были заполнены
        model.addAttribute("person", personService.show(id));

        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, Person person) {
        // отправляем в DAO получаенные данные чтобы обновить человека
        personService.update(id, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        personService.delete(id);

        return "redirect:/people";
    }
}
