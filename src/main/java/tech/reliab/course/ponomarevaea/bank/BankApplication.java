package tech.reliab.course.ponomarevaea.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.reliab.course.ponomarevaea.bank.entity.*;
import tech.reliab.course.ponomarevaea.bank.service.*;
import tech.reliab.course.ponomarevaea.bank.service.impl.*;
import tech.reliab.course.ponomarevaea.bank.utils.StatusATM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);

		PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl();
		BankService bankService = new BankServiceImpl();
		BankOfficeService bankOfficeService = new BankOfficeServiceImpl();
		UserService userService = new UserServiceImpl();
		AtmService atmService = new AtmServiceImpl();
		EmployeeService employeeService = new EmployeeServiceImpl();
		CreditAccountService creditAccountService = new CreditAccountServiceImpl();

		User user = userService.create(5, "Jane", "Ivanov", new Date(29091989), "Teacher");
		BankATM bankATM = atmService.create(2, "CoolAtm", StatusATM.Work, 134000.0);
		Employee employee = employeeService.create(4, "Sergey", "Petrov", new Date(19081917), "Manager", 115000.0);
		BankOffice bankOffice = bankOfficeService.create(3, "VTB office", "Tver, Popova str, 15", true, 300000.0);
		Bank bank = bankService.create(1, "VTB", bankOffice, bankATM, employee, user);

		bankOfficeService.addATM(bankOffice, bankATM);
		bankOfficeService.addEmployee(bankOffice, employee);

		employeeService.setWorkerToBankomat(bankATM, employee);

		atmService.addMoney(bankATM, 1221200.0);
		paymentAccountService.addPayment(8, user, bank);
		ArrayList<PaymentAccount> acc = user.getPaymentAccounts();
		paymentAccountService.addMoney(acc.get(0), 120000.0);

		bankService.addMoney(bank, 1200000.0);
		bankOfficeService.addMoney(bankOffice, 32134243.0);

		creditAccountService.openCredit(9, user, bankOffice, employee, LocalDate.now(), 12, 12000.0);


		String b = bank.toString();
		System.out.println(b);

		b = bankOffice.toString();
		System.out.println(b);

		b = bankATM.toString();
		System.out.println(b);

		b = employee.toString();
		System.out.println(b);

		b = userService.getInfo(user);
		System.out.println(b);
	}

}
