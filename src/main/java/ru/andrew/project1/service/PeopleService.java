package ru.andrew.project1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrew.project1.models.Person;
import ru.andrew.project1.repositories.PeopleRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    // Метод для проверки, что date2 на 10 или более дней позже date1
    public static boolean isMoreThan10Days(Date date1, Date date2) {
        // Получаем разницу между датами в миллисекундах
        long differenceInMillis = date2.getTime() - date1.getTime();

        // Переводим разницу в дни (миллисекунды / количество миллисекунд в одном дне)
        long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMillis);

        // Проверяем, что разница больше или равна 10 дням
        return differenceInDays >= 10;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findById(int id){
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }

    public Optional<Person> findByFullName(String fullName){
        return peopleRepository.findByFullName(fullName);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson){
        updatePerson.setPersonId(id);
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

}
