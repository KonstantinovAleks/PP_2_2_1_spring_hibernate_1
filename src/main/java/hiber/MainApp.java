package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Ivan", "Ivanov", "iv_iv@mail.ru");
        User user2 = new User("Petr", "Petrov", "pet_pet@mail.ru");
        User user3 = new User("Ignat", "Ignatov", "ig_ig@mail.ru");
        User user4 = new User("Aleksandr", "Aleksandrov", "al_al@mail.ru");

        Car car1 = new Car("Kia Rio", 741);
        Car car2 = new Car("Reno", 621);
        Car car3 = new Car("Pego", 376);
        Car car4 = new Car("Geely", 345);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println("Пользователь с выбранной моделью и серией машины" + userService.getUserByCarModelAndSeries("Kia Rio", 741));

        context.close();
    }
}
