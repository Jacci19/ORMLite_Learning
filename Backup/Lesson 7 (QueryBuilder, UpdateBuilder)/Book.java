package pl.ormlite.example;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;


@DatabaseTable(tableName = "books")
public class Book {                                                         //ta klasa będzie tabelą w naszej BD

    public Book(){                                                          //konstruktor

    }


    //http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#Persisted-Types          - mapowanie typów javy na typy ORMLite

    @DatabaseField(generatedId = true)                                              //aby nasze id było generowane automatycznie i miało unikalne wartości
    private int id;

    @DatabaseField(columnName = "TITLE", canBeNull = false)                             //jak nie wpiszemy columnName to zostanie użyty ten ze Stringa (title), tytuł nie może być null
    private String title;

    @DatabaseField(columnName = "DESCRIPTION", dataType = DataType.LONG_STRING)         //aby można było wpisywać dłuższe stringi niż 256 znaków
    private String description;

    @DatabaseField(columnName = "ISBN", unique = true)                              //aby elementy były unikalne (nie powtarzały się)
    private String isbn;

    @DatabaseField(columnName = "ADDED_DATE")
    private Date addedDate;                                                         //data dodania książki do bazy danych

    @DatabaseField(columnName = "DATE_RELEASE", dataType = DataType.DATE_STRING, format = "yyyy-MM-DD")
    private Date dateRelease;                                                       //data wydania książki

    @DatabaseField(columnName = "RATING", width = 1)                                //szerokość tego stringa to max 1 znak (nieobsługiwane przez sqlite)
    private String rating;

    @DatabaseField(columnName = "BORROWED", defaultValue = "false")                 //domyslna wartość
    private boolean borrowed;                                                       //czy książka jest pożyczona czy też jest u mnie

    @DatabaseField(columnName = "PRICE")
    private double price;

                                                                                                    //gettery
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIsbn() {
        return isbn;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public Date getDateRelease() {
        return dateRelease;
    }

    public String getRating() {
        return rating;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }                                                   //settery

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public void setDateRelease(Date dateRelease) {
        this.dateRelease = dateRelease;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", addedDate=" + addedDate +
                ", dateRelease=" + dateRelease +
                ", rating='" + rating + '\'' +
                ", borrowed=" + borrowed +
                ", price=" + price +
                '}';
    }
}
