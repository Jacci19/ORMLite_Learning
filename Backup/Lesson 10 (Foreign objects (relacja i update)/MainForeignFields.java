//https://www.youtube.com/watch?v=0xCpwNbbBc8&list=PLpzwMkmxJDUzSwjdC5nVEY9h3rdfgXX7V
//SQLite Studio - tego używam

package pl.ormlite.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainForeignFields {

    public static void main(String[] args) throws SQLException, IOException, ParseException {

        String databaseUrl = "jdbc:sqlite:database.db";                                     //sterownik do bazy danych (BD) : nazwa BD (ew. poprzedzona ścieżką), tu będzie w głównym katalogu projektu
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);          //połączenie z naszą bazą danych

        TableUtils.dropTable(connectionSource, Book.class, true);                //przebudowa BD, usuwamy stare tabele, żeby potem stworzyć nowe.
        TableUtils.dropTable(connectionSource, Author.class, true);
        TableUtils.createTable(connectionSource, Book.class);                               //podpięcie tabel do BD poprzez ORMLite
        TableUtils.createTable(connectionSource, Author.class);

        Dao<Book, Integer> daoBook = DaoManager.createDao(connectionSource, Book.class);            //<Klasa elementów bazy, klasa ID> Dao umożliwia przeróżne operacje na BD
        Dao<Author, Integer> daoAuthor = DaoManager.createDao(connectionSource, Author.class);      //<Klasa elementów bazy, klasa ID> Dao umożliwia przeróżne operacje na BD

        Author author = new Author();
        author.setName("King");

        //książka
        Book book = new Book();                                                              //wprowadzamy jedną książkę do bazy
        book.setTitle("Carrie");
        book.setDescription("Jakiś opis ksiązki o dziwnej dziewczynce");
        book.setIsbn("22222");
        book.setAddedDate(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString = "2018/11/11";
        Date date2 = sdf.parse(dateInString);
        book.setDateRelease(date2);
        book.setRating("4");
        book.setBorrowed(true);
        book.setPrice(28.45);

/*  //tak musimy zrobić gdy w Book nie wpiszemy: foreignAutoCreate = true, foreignAutoRefresh = true

        daoAuthor.create(author);
        book.setAuthor(author);
        daoBook.create(book);

        daoAuthor.refresh(book.getAuthor());                            //trzeba odświeżyć, żeby nie pokazywał null przy danych autora
        System.out.println("Po zapisie do bazy danych: " + book);

        Book book2 = daoBook.queryForId(1);
        daoAuthor.refresh(book2.getAuthor());                           //trzeba odświeżyć, żeby nie pokazywał null przy danych autora
        System.out.println("Po zapytaniu do bazy danych: " + book2);
*/

    //... a tak, gdy to wpiszemy (foreignAutoCreate = true, foreignAutoRefresh = true ) - 3 linie mniej
        book.setAuthor(author);
        daoBook.create(book);

        System.out.println("\nPo zapisie do bazy danych: " + book);         //tu book są jeszcze null...
        Book book2 = daoBook.queryForId(1);
        System.out.println("\nPo zapytaniu do bazy danych: " + book2);      //...ale tu już nie


        book2.getAuthor().setName("Nieznany");
        //daoBook.createOrUpdate(book2);                                    //niepotrzebne
        book2 = daoBook.queryForId(1);
        System.out.println("\nPo zmianie autora: " + book2);                //name autora nie zmieniło się, ormLite nie zapewnia takich auto updejtów
        daoAuthor.createOrUpdate(book2.getAuthor());
        System.out.println("\nPo zmianie autora i updejcie: " + book2);                //teraz name autora zmieniło się


        author = daoAuthor.queryForId(1);
        author.getBooks().forEach(e->{
            e.setTitle("Bla-bla-bla");

            try {                                                           //po zmianie tytułu trzeba robić updejty żeby sie odświezyło w BD
                daoBook.createOrUpdate(e);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        daoAuthor.refresh(author);
        author.getBooks().forEach(e->{
            System.out.println("\nZmiana tytułu: " + e.getTitle());         //tytuł się zmienił
        });


        //trzecia książka
        Book book3 = new Book();
        book3.setTitle("Forrest Gump");
        book3.setDescription("Jakiś opis foresta");
        book3.setIsbn("33333");
        book3.setAddedDate(new Date());

        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString3 = "2018/11/11";
        Date date3 = sdf.parse(dateInString3);

        book3.setDateRelease(date3);
        book3.setRating("3");
        book3.setBorrowed(true);
        book3.setPrice(32.50);

        author.getBooks().add(book3);
        daoAuthor.createOrUpdate(author);



        connectionSource.close();
    }
}




/*   NOTATKI

TableUtils.createTableIfNotExists(connectionSource, Book.class);                    //tworzy tabelę tylko wtedy gdy ona nie istnieje (chroni przed nadpisaniem starej)

Jeśli próbujemy stworzyć tabelę w BD a ona już istnieje w BD to wyskoczy wyjątek.

Inne adnotacje można zobaczyć tu (2.1.1 Adding ORMLite Annotations):
http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#Persisted-Types

http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#Raw-Statements - surowe zapytania

W bazie sqLite boolean ma wartośc (0,1). W bazie H2 może mieć (0,1) lub (true,false).

Kolekcje pobierane są w sposób "lazy". Nie są pobierane dopóki nie zwrócimy się o to w kodzie.

*/