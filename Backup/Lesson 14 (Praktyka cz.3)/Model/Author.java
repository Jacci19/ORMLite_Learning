package pl.ormlite.example.Model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Author implements BaseModel{

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @ForeignCollectionField (eager = true)                              //jeśli chcemy żeby przy zapytaniu pobierane były od razu wszystkie książki autora (może spowalniać działanie programu)
    private ForeignCollection<Book> books;                              //wszystkie książki danego autora (działa gdy w klasie book jest pole foreign: Author
                                                                        //nie jest to od razu widoczne w np. sqlite studio

    public int getId() {                                                //GETTERY
        return id;
    }
    public String getName() {
        return name;
    }
    public ForeignCollection<Book> getBooks() {
        return books;
    }

    public void setId(int id) {                                         //SETTERY
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBooks(ForeignCollection<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
