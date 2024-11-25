package tech.reliab.course.ponomarevaea.bank.service.impl;

import tech.reliab.course.ponomarevaea.bank.entity.CreditAccount;
import tech.reliab.course.ponomarevaea.bank.entity.PaymentAccount;
import tech.reliab.course.ponomarevaea.bank.entity.User;
import tech.reliab.course.ponomarevaea.bank.service.UserService;

import java.util.Date;


public class UserServiceImpl implements UserService {

    @Override
    public User create(Integer id, String name, String surname, Date birthday, String work) {
        return new User(id, name, surname, birthday, work);
    }

    @Override
    public void changeWork(User user, String newWork, Double newMonthSalary) {
        user.setWork(newWork);
        user.setMonthSalary(newMonthSalary);
        int creditRating = 0;
        int startRat = 0;
        int endRat = 1000;
        while ((startRat != 10000) && (creditRating == 0)) {
            if ((newMonthSalary <= endRat) && (newMonthSalary >= startRat))
                creditRating = endRat / 10;
            else {
                startRat += 1000;
                endRat += 1000;
            }
        }
        user.setCreditRating(creditRating);
    }

    @Override
    public String getInfo(User user){
        String res = "";

        res += user.toString();

        res += "\n\nИнформация о платёжных счётах:\n";
        for (PaymentAccount paymentAccount: user.getPaymentAccounts()){
            res += paymentAccount.toString();
            res += "\n\n";
        }

        res += "\n\nИнформация о кредитных счётах:\n";
        for (CreditAccount creditAccount: user.getCreditAccounts()){
            res += creditAccount.toString();
            res += "\n\n";
        }
        return res;
    }


}