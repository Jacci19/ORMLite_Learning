package pl.ormlite.example;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import pl.ormlite.example.Dao.AuthorDao;
import pl.ormlite.example.Model.Author;
import pl.ormlite.example.Utils.DataCreator;
import pl.ormlite.example.Utils.DbManager;

import java.sql.SQLException;

public class MainDbManager {

    public static void main(String[] args) throws SQLException {
        DbManager.initDatabase();

        Author author = DataCreator.author();
        AuthorDao authorDao = new AuthorDao(DbManager.getConnectionSource());
        authorDao.creatOrUpdate(Author.class, author);

        DbManager.closeConnectionSource();
    }
}
