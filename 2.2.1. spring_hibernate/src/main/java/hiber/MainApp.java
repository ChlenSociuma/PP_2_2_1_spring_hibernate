package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru")
              .setCar(new Car("Audi", 1111)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru")
              .setCar(new Car("BMW", 2222)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru")
              .setCar(new Car("VAZ", 3333)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru")
              .setCar(new Car("UAZ", 4444)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         Car car = user.getCar();
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car Model = "+car.getModel());
         System.out.println("Car Series = "+car.getSeries());
         System.out.println();
      }

      Optional<User> userOp = userService.findUserByCar("Audi", 1111);

      userOp.ifPresent(System.out::println);

      context.close();
   }
}
