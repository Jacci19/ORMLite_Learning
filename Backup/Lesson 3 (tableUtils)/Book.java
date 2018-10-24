package pl.ormlite.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "books")
public class Book {                                                         //ta klasa będzie tabelą w naszej BD

    public Book(){                                                          //konstruktor

    }

    @DatabaseField(generatedId = true)                              //aby nasze id było generowane automatycznie i miało unikalne wartości
    private int id;

    //http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#Persisted-Types          - mapowanie typów javy na typy ORMLite

    @DatabaseField(columnName = "TITLE")                            //jak nie wpiszemy columnName to zostanie użyty ten ze Stringa (title)
    private String title;

    @DatabaseField(columnName = "PRICE")
    private Double price;

}
