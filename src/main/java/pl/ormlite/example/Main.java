//https://www.youtube.com/watch?v=0xCpwNbbBc8&list=PLpzwMkmxJDUzSwjdC5nVEY9h3rdfgXX7V

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
        String databaseUrl = "jdbc:sqlite:database.db";                         //sterownik do bazy danych (BD) : nazwa BD (ew. poprzedzona ścieżką), tu będzie w głównym katalogu projektu
        //String databaseUrlH2 = "jdbc:h2:./database";                          //tak to wygląda w przypadku bazy H2

        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
        /*ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrlH2, "userName", "password");
        tak to wygląda dla H2. W pom trzeba jeszcze wkleić odpowiednie dependency (z mvnrepository.com). "userName", "password" są opcjonalne.
        z www.h2database.com trzeba ściągnąć klienta żeby obsłużyć taką bazę
        */

        Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
        TableUtils.createTable(connectionSource, Account.class);

        Account account = new Account();
        account.setName("Jim Coakley");
        account.setPassword("tajne");
        accountDao.create(account);


        connectionSource.close();
    }

}