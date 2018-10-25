package pl.ormlite.example.Utils;

import pl.ormlite.example.Model.Author;
import pl.ormlite.example.Model.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DataCreator {

    public static Book firstBook() {
        Book book = new Book();
        book.setTitle("Władca pierścieni");
        book.setDescription("Ksiązka o pierścieniu");
        book.setIsbn("11111");
        book.setAddedDate(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString = "2012/11/11";
        Date date = null;
        try {
            date = sdf.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        book.setDateRelease(date);
        book.setRating("1");
        book.setBorrowed(true);
        book.setPrice(67.99);
        return book;
    }

    public static Book secondBook() {
        Book book2 = new Book();
        book2.setTitle("Hobbit");
        book2.setDescription("Niesamowite przygody małego hobbita");
        book2.setIsbn("22222");
        book2.setAddedDate(new Date());

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString2 = "2012/11/11";
        Date date2 = null;
        try {
            date2 = sdf2.parse(dateInString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        book2.setDateRelease(date2);
        book2.setRating("1");
        book2.setBorrowed(true);
        book2.setPrice(22.99);
        return book2;
    }

    public static Book thirdBook() {
        Book book2 = new Book();
        book2.setTitle("Hobbit");
        book2.setDescription("Niesamowite przygody małego hobbita");
        book2.setIsbn("22222");
        book2.setAddedDate(new Date());

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString2 = "2012/11/11";
        Date date2 = null;
        try {
            date2 = sdf2.parse(dateInString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        book2.setDateRelease(date2);
        book2.setRating("1");
        book2.setBorrowed(true);
        book2.setPrice(22.99);
        return book2;
    }

    public static Author author() {
        Author author = new Author();
        author.setName("Tolkien");
        return author;
    }
}