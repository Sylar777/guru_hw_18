package models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PostBooksRequestJson {
    private String userId;
    private ArrayList<CollectionOfIsbns> collectionOfIsbns;

    public void addIsbns(String isbn) {
        CollectionOfIsbns collectionOfIsbns = new CollectionOfIsbns();
        collectionOfIsbns.setIsbn(isbn);

        ArrayList<CollectionOfIsbns> isbnData = new ArrayList<>();
        isbnData.add(collectionOfIsbns);
        this.collectionOfIsbns = isbnData;
    }
}
