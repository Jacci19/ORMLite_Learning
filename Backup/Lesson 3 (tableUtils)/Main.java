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

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        String databaseUrl = "jdbc:sqlite:database.db";                                     //sterownik do bazy danych (BD) : nazwa BD (ew. poprzedzona ścieżką), tu będzie w głównym katalogu projektu
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);          //połączenie z naszą bazą danych

        TableUtils.dropTable(connectionSource, Book.class, true);                //przebudowa BD, usuwamy starą tabelę, żeby potem stworzyć nową.
        TableUtils.createTable(connectionSource, Book.class);                               //podpięcie tabeli do BD poprzez ORMLite

        connectionSource.close();
    }

}

/*
TableUtils.createTableIfNotExists(connectionSource, Book.class);                    //tworzy tabelę tylko wtedy gdy ona nie istnieje (chroni przed nadpisaniem starej)

jeśli próbujemy stworzyć tabelę w BD a ona już istnieje w BD to wyskoczy wyjątek.

*/