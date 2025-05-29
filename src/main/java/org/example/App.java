package org.example;

import org.example.config.JdbcConfig;
import org.example.dao.PostDao;
import org.example.dao.UserDao;
import org.example.dao.daoImpl.PostDaoImpl;
import org.example.dao.daoImpl.UserDaoIml;
import org.example.models.Post;
import org.example.models.User;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
       UserDao userDao = new UserDaoIml();
//        userDao.createUserTable();
        User user = new User("Sanjar","s@gmail.com","1234");
//        userDao.createUser(user);
//        System.out.println(userDao.getUsers());

        PostDao postDao = new PostDaoImpl();
        //postDao.crateTable();
        Post post = new Post("surot","symbat", LocalDate.of(2017,3,7),1L);
        //postDao.savePost(post);
        //System.out.println(postDao.getPosts());
        //postDao.deletePost(2L);
        //System.out.println(userDao.searchusersByName("Sanjar"));
        //System.out.println(userDao.getUserById(1L));
        //User newUser = new User("Symbat","simba@gmail.com","5555");
        //userDao.updateUser(1L,newUser);
    }
}
