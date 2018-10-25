//https://www.youtube.com/watch?v=0xCpwNbbBc8&list=PLpzwMkmxJDUzSwjdC5nVEY9h3rdfgXX7V
//SQLite Studio - tego używam

package pl.ormlite.example.Main;

        import com.j256.ormlite.dao.Dao;
        import com.j256.ormlite.dao.DaoManager;
        import com.j256.ormlite.dao.GenericRawResults;
        import com.j256.ormlite.jdbc.JdbcConnectionSource;
        import com.j256.ormlite.support.ConnectionSource;
        import com.j256.ormlite.table.TableUtils;
        import pl.ormlite.example.Model.Book;

        import java.io.IOException;
        import java.sql.SQLException;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, IOException, ParseException {

        String databaseUrl = "jdbc:sqlite:database.db";                                     //sterownik do bazy danych (BD) : nazwa BD (ew. poprzedzona ścieżką), tu będzie w głównym katalogu projektu
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);          //połączenie z naszą bazą danych

        TableUtils.dropTable(connectionSource, Book.class, true);                //przebudowa BD, usuwamy starą tabelę, żeby potem stworzyć nową.
        TableUtils.createTable(connectionSource, Book.class);                               //podpięcie tabeli do BD poprzez ORMLite

        //pierwsza książka
        Book book = new Book();                                                              //wprowadzamy jedną książkę do bazy
        book.setTitle("Władca pierścieni");
        book.setDescription("Jakiś opis ksiązki o pierścieniu");
        book.setIsbn("11111");
        book.setAddedDate(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString = "2018/11/11";
        Date date = sdf.parse(dateInString);

        book.setDateRelease(date);
        book.setRating("1");
        book.setBorrowed(true);
        book.setPrice(33.99);

        //druga książka
        Book book2 = new Book();                                                              //wprowadzamy jedną książkę do bazy
        book2.setTitle("Carrie");
        book2.setDescription("Jakiś opis ksiązki o dziwnej dziewczynce");
        book2.setIsbn("22222");
        book2.setAddedDate(new Date());

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString2 = "2018/11/11";
        Date date2 = sdf.parse(dateInString2);

        book2.setDateRelease(date2);
        book2.setRating("4");
        book2.setBorrowed(true);
        book2.setPrice(28.45);

        //trzecia książka
        Book book3 = new Book();                                                              //wprowadzamy jedną książkę do bazy
        book3.setTitle("Forrest Gump");
        book3.setDescription("Jakiś opis ksiązki o Forescie");
        book3.setIsbn("33333");
        book3.setAddedDate(new Date());

        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd");
        String dateInString3 = "2018/11/11";
        Date date3 = sdf.parse(dateInString3);

        book3.setDateRelease(date3);
        book3.setRating("3");
        book3.setBorrowed(true);
        book3.setPrice(32.50);


        //dodanie książek do bazy
        Dao<Book, Integer> dao = DaoManager.createDao(connectionSource, Book.class);        //<Klasa elementów bazy, klasa ID> Dao umożliwia przeróżne operacje na BD
        dao.create(book);
        dao.create(book2);
        dao.create(book3);

        System.out.println("_______________Pierwsze zapytanie_______________");
        GenericRawResults<String[]> rawResult = dao.queryRaw("SELECT * FROM books");     //wybierz wszystkie elementy z bazy books i przypisz je do rawResult
        List<String[]> result = rawResult.getResults();                                     //Lista zawierająca tablice stringów

        result.forEach(e->{                                                                 //wypisanie rezultatu zapytania
            for (String s: e) {
                System.out.println(s);
            }
            System.out.println("__________");
        });

        System.out.println("_______________Drugie zapytanie_______________");
        GenericRawResults<String[]> where = dao.queryRaw("SELECT * FROM books WHERE title = 'Carrie'");      //wybierz wszystkie elementy z bazy books które mają tytuł Carrie
        List<String[]> resultsWhere = where.getResults();                                                       //Lista zawierająca tablice stringów

        resultsWhere.forEach(e->{                                                                               //wypisanie rezultatu zapytania
            for (String s: e) {
                System.out.println(s);
            }
        });

        System.out.println("_______________Trzecie zapytanie_______________");
        GenericRawResults<String[]> selectMinMax = dao.queryRaw("SELECT MIN(price), MAX(price) FROM books");         //znajdź najniższą i najwyższą cenę książek
        List<String[]> resultsMinMax = selectMinMax.getResults();                                                       //Lista zawierająca tablice stringów

        resultsMinMax.forEach(e->{                                                                                      //wypisanie rezultatu zapytania
            for (String s: e) {
                System.out.println(s);
            }
        });

        System.out.println("_______________Czwarte zapytanie_______________");
        GenericRawResults<String[]> selectCount = dao.queryRaw("SELECT count(*) FROM books WHERE borrowed = 1");     //policz książki, które są pożyczone
        List<String[]> resultsCount = selectCount.getResults();                                                         //Lista zawierająca tablice stringów

        resultsCount.forEach(e->{                                                                                      //wypisanie rezultatu zapytania
            for (String s: e) {
                System.out.println(s);
            }
        });

        System.out.println("_______________Prostszy sposób na otrzymanie pojedynczego wyniku z BD:_______________");
        double avgUnits = dao.queryRawValue("SELECT AVG(price) FROM books");
        System.out.println("Srednia cena książek wynosi " + avgUnits);

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

*/