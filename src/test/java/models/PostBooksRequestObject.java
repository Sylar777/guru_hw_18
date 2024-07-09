package models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PostBooksRequestObject {
    private String userId;
    private ArrayList<models.collectionOfIsbns> collectionOfIsbns;

    public void addIsbns(String isbn) {
        models.collectionOfIsbns collectionOfIsbns = new collectionOfIsbns();
        collectionOfIsbns.setIsbn(isbn);

        ArrayList<models.collectionOfIsbns> isbnData = new ArrayList<>();
        isbnData.add(collectionOfIsbns);
        this.collectionOfIsbns = isbnData;
    }
}
