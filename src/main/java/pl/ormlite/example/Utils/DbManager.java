package pl.ormlite.example.Utils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.ormlite.example.Model.Author;
import pl.ormlite.example.Model.Book;

import java.io.IOException;
import java.sql.SQLException;


public class DbManager {                                                                //klasa ze statycznymi metodami narzędziowymi.

    public static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_SQLITE = "jdbc:sqlite:database";
    private static ConnectionSource connectionSource;

    public static void initDatabase(){                                                     //inicjowanie BD (usuwa tabele i je potem tworzy),
        createConnectionSource();
        dropTable();                                                                        //metody zdefiniowane niżej
        createTable();                                                                      // w normalnym programie tak nie można (żeby za każdym razem usuwać tabelę i tworzyć ją ponownie)
        closeConnectionSource();
    }


    private static void createConnectionSource(){                                                 //Tworzenie połączenia
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_SQLITE);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource() {                                  //Pobieranie połączenia
        if (connectionSource == null){
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource(){                                                   //Zamykanie połączenia
        if (connectionSource != null){
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private static void createTable(){                                                      //Tworzenie tabeli
        try {
            TableUtils.createTableIfNotExists(connectionSource, Author.class);
            TableUtils.createTableIfNotExists(connectionSource, Book.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private static void dropTable(){                                                        //Usuwanie tabeli
        try {
            TableUtils.dropTable(connectionSource, Author.class, true);
            TableUtils.dropTable(connectionSource, Book.class, true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
