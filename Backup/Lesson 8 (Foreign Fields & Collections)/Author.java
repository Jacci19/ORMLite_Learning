package pl.ormlite.example;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Author {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @ForeignCollectionField (eager = true)                              //jeśli chcemy żeby przy zapytaniu pobierane były od razu wszystkie książki autora (może spowalniać działanie programu)
    private ForeignCollection<Book> books;                              //wszystkie książki danego autora (działa gdy w klasie book jest pole foreign: Author
                                                                        //nie jest to od razu widoczne w np. sqlite studio
}
