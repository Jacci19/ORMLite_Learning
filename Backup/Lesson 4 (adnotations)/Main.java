//https://www.youtube.com/watch?v=0xCpwNbbBc8&list=PLpzwMkmxJDUzSwjdC5nVEY9h3rdfgXX7V
//SQLite Studio - tego używam

package pl.ormlite.example;

        import com.j256.ormlite.dao.Dao;
        import com.j256.ormlite.dao.DaoManager;
        import com.j256.ormlite.jdbc.JdbcConnectionSource;
        import com.j256.ormlite.support.ConnectionSource;
        import com.j256.ormlite.table.TableUtils;

        import java.io.IOException;
        import java.sql.SQLException;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class Main {

    public static void main(String[] args) throws SQLException, IOException, ParseException {

        String databaseUrl = "jdbc:sqlite:database.db";                                     //sterownik do bazy danych (BD) : nazwa BD (ew. poprzedzona ścieżką), tu będzie w głównym katalogu projektu
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);          //połączenie z naszą bazą danych

        TableUtils.dropTable(connectionSource, Book.class, true);                //przebudowa BD, usuwamy starą tabelę, żeby potem stworzyć nową.
        TableUtils.createTable(connectionSource, Book.class);                               //podpięcie tabeli do BD poprzez ORMLite



        Book book = new Book();                                                              //wprowadzamy jedną książkę do bazy
        book.setTitle("Władca pierścieni");
        book.setIsbn("11111");
        book.setAddedDate(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString = "2012/11/11";
        Date date = sdf.parse(dateInString);

        book.setDateRelease(date);
        book.setRating("1");
        book.setBorrowed(true);
        book.setPrice(33.99);

        Dao<Book, ?> dao = DaoManager.createDao(connectionSource, Book.class);
        dao.create(book);

        connectionSource.close();
    }

}

/*   NOTATKI

TableUtils.createTableIfNotExists(connectionSource, Book.class);                    //tworzy tabelę tylko wtedy gdy ona nie istnieje (chroni przed nadpisaniem starej)

Jeśli próbujemy stworzyć tabelę w BD a ona już istnieje w BD to wyskoczy wyjątek.

Inne adnotacje można zobaczyć tu (2.1.1 Adding ORMLite Annotations):
http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#Persisted-Types
*/