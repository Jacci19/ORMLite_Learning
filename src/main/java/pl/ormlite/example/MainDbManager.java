package pl.ormlite.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import pl.ormlite.example.Model.Author;
import pl.ormlite.example.Utils.DataCreator;
import pl.ormlite.example.Utils.DbManager;

import java.sql.SQLException;

public class MainDbManager {

    public static void main(String[] args) throws SQLException {
        DbManager.initDatabase();

        Author author = DataCreator.author();
        Dao<Author, Integer> daoAuthor = DaoManager.createDao(DbManager.getConnectionSource(), Author.class);
        daoAuthor.createOrUpdate(author);
        DbManager.closeConnectionSource();
    }
}
