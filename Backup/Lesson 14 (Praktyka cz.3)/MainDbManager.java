package pl.ormlite.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import pl.ormlite.example.Dao.AuthorDao;
import pl.ormlite.example.Dao.BookDao;
import pl.ormlite.example.Model.Author;
import pl.ormlite.example.Model.Book;
import pl.ormlite.example.Utils.DataCreator;
import pl.ormlite.example.Utils.DbManager;

import java.sql.SQLException;
import java.util.List;

public class MainDbManager {

    public static void main(String[] args) throws SQLException {
        DbManager.initDatabase();

        Author author = DataCreator.author();
        AuthorDao authorDao = new AuthorDao(DbManager.getConnectionSource());
        authorDao.creatOrUpdate(author);
        List<Author> lista = authorDao.queryForAll(Author.class);
        lista.forEach(e->{
            System.out.println(e);
        });

        Book book = DataCreator.firstBook();
        BookDao bookDao = new BookDao(DbManager.getConnectionSource());
        bookDao.creatOrUpdate(book);
        bookDao.queryWhere("TITLE", "Władca pierścieni");

        DbManager.closeConnectionSource();
    }
}



/*   NOTATKI

Nie robię tego w various projects bo to jest na mavenie.
Klasy z katalogu main nie są wykorzystywane w końcowym projekcie (lekcja 14)


TableUtils.createTableIfNotExists(connectionSource, Book.class);                    //tworzy tabelę tylko wtedy gdy ona nie istnieje (chroni przed nadpisaniem starej)

Jeśli próbujemy stworzyć tabelę w BD a ona już istnieje w BD to wyskoczy wyjątek.

Inne adnotacje można zobaczyć tu (2.1.1 Adding ORMLite Annotations):
http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#Persisted-Types

http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#Raw-Statements - surowe zapytania

W bazie sqLite boolean ma wartośc (0,1). W bazie H2 może mieć (0,1) lub (true,false).

*/