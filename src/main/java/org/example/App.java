package org.example;

import org.example.config.JdbcConfig;
import org.example.dao.CommentDao;
import org.example.dao.PostDao;
import org.example.dao.UserDao;
import org.example.dao.daoImpl.CommentDaoImpl;
import org.example.dao.daoImpl.PostDaoImpl;
import org.example.dao.daoImpl.UserDaoIml;
import org.example.models.Comment;
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
        User user = new User("Jenish","s@gmail.com","1234");
      //userDao.createUser(user);
//        System.out.println(userDao.getUsers());

        PostDao postDao = new PostDaoImpl();
        //postDao.crateTable();
        Post post = new Post("kar455","symbat", LocalDate.of(2017,3,7),2L);
        //postDao.savePost(post);
        //System.out.println(postDao.getPosts());
        //postDao.deletePost(2L);
        //System.out.println(userDao.searchusersByName("Sanjar"));
        //System.out.println(userDao.getUserById(1L));
        //User newUser = new User("Symbat","simba@gmail.com","5555");
        //userDao.updateUser(1L,newUser);
        //System.out.println(postDao.getPostById(1L));
//        Post newPost = new Post("image","ysyk_kol", LocalDate.of(2011,3,7),1L);
//        postDao.updatePost(1L,newPost);
        //System.out.println(postDao.countPostsByUser(1L));
        CommentDao commentDao = new CommentDaoImpl();
        //commentDao.createTable();
        Comment comment = new Comment("rrrrr",LocalDate.of(2020,1,1),1L,1L);
        //commentDao.saveComment(comment);
        //System.out.println(commentDao.getComments());
        //System.out.println(commentDao.getCommentById(1L));
        //Comment newComment = new Comment("bir eki",LocalDate.of(2003,3,3),1L,1L);
        //commentDao.updateComment(1L,newComment);
        //commentDao.deleteCommentById(1L);
        //System.out.println(commentDao.findRecentCommentsByPost(1L, 2));
        System.out.println(commentDao.countCommentsByPost(1L));
    }
}
