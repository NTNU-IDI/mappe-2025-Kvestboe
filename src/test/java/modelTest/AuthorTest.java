package modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.iir.bidata.model.Author;
import org.junit.jupiter.api.Test;

public class AuthorTest {

  @Test
  void testAuthorReturnsName() {
    Author author = new Author("Kristian");
    assertEquals("Kristian", author.getName(), "Author's name should be Kristian");
  }

}
