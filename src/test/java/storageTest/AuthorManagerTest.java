package storageTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.iir.bidata.storage.AuthorManager;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthorManagerTest {
  AuthorManager authorManager;

  @BeforeEach
  void setUp() {
    authorManager = new AuthorManager();

  }

  @Test
  void authorManagerAddAuthorTest() {
    ArrayList<String> authors = new ArrayList<>();
    authors.add("Kristian");

    authorManager.addAuthor("Kristian");
    assertEquals(
        "Kristian",
        authorManager.getAuthor("Kristian").getName(),
        "AuthorManager should return author with name Kristian"
    );
    assertEquals(
        authors,
        authorManager.getAuthors(),
        "AuthorManager should return ArrayList of one string with value Kristian"
    );

  }

  @Test
  void authorManagerGettersTest() {
    ArrayList<String> authors = new ArrayList<>();
    authors.add("Kristian");
    authors.add("Elon Musk");
    authors.add("Obama");

    authorManager.addAuthor("Kristian");
    authorManager.addAuthor("Elon Musk");

    authorManager.addAuthor("Kristian");
    assertEquals(
        "Kristian",
        authorManager.getAuthor("Kristian").getName(),
        "AuthorManager should return author with name Kristian"
    );

    assertEquals(
        authors,
        authorManager.getAuthors(),
        "AuthorManager should return ArrayList of one string with same value "
            + "Kristian, Elon Musk and Obama"
    );

  }



}
