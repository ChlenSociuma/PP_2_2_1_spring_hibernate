package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public User findUserByCar(String model, int series) {
      try(Session session = sessionFactory.openSession()) {
         Transaction tx = session.beginTransaction();
         String hql = "SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series";
         Query<User> query = session.createQuery(hql, User.class);
         query.setParameter("model", model);
         query.setParameter("series", series);

         return query.uniqueResult();
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("Ошибка при поиске юзера по авто.");
      }
      return null;
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
