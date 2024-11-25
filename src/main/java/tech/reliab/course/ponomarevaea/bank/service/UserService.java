package tech.reliab.course.ponomarevaea.bank.service;

import tech.reliab.course.ponomarevaea.bank.entity.User;

import java.util.Date;

public interface UserService {
    /*Создание пользователя*/
    User create(Integer id, String name, String surname, Date birthday, String work);
    /*Смена пользователем работы, а соответственно, и заработной платы, и пересчёт его кредитного рейтинга*/
    void changeWork(User user, String newWork, Double newMonthSalary);
    //Вывести информацию о клиенте
    String getInfo(User user);


}
