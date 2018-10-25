package pl.ormlite.example.Dao;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import pl.ormlite.example.Model.Book;

import java.sql.SQLException;
import java.util.List;

public class BookDao extends CommonDao {

    public BookDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    public List<String[]> queryRaw(String value) throws SQLException {
        GenericRawResults<String[]> where = getDao(Book.class).queryRaw("SELECT * FROM books WHERE title = 'Hobbit'");
        return where.getResults();
    }

    public List<Book> queryWhere(String columnName, String value) throws SQLException {
        QueryBuilder<Book, Integer> queryBuilder = getQueryBuilder(Book.class);
        queryBuilder.where().eq(columnName, value);
        PreparedQuery<Book> prepare = queryBuilder.prepare();
        return getDao(Book.class).query(prepare);
    }

/*                                                                                                     //przykład z lekcji 14
    public void queryWhere(String columnName, String value) throws SQLException {
        QueryBuilder<Book, Integer> queryBuilder = getQueryBuilder(Book.class);
        queryBuilder.where().eq(columnName, value);
        PreparedQuery<Book> prepare = queryBuilder.prepare();

        List<Book> result = getDao(Book.class).query(prepare);
        result.forEach(e->{
            System.out.println("\n" + e + "\n");
        });
    }
*/

}
