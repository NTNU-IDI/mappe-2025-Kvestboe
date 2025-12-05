package modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntryTest {
  Author author;
  Entry entry;
  ArrayList<String> tags;

  @BeforeEach
  void setup() {
    author = new Author("Kristian");
    tags = new ArrayList<>(List.of("test1", "test2"));

    entry = new Entry(author, "test", tags, "test content");
  }

  @Test
   void testEntryGetters() {
    assertEquals("Kristian", author.getName(),  "Author's name should be Kristian");
    assertEquals("test", entry.getTitle(),  "Entry's title should be test");
    assertEquals(tags, entry.getTags(), "Entry's tags should be same as tags");
    assertEquals("test content", entry.getContent(),  "Entry's content should be test content");
    assertEquals(LocalDate.now(), entry.getDate(), "Entry's date should be current date");
  }

  @Test
  void testEntrySetters() {
    Author newAuthor =  new Author("Elon Musk");
    ArrayList<String> newTags = new ArrayList<>(List.of("works1", "works2"));
    LocalDate newDate = LocalDate.of(2025, 12, 4);

    entry.setAuthor(newAuthor);
    entry.setTitle("works title");
    entry.setTags(newTags);
    entry.setContent("works content");
    entry.setDate(newDate);

    assertEquals("Elon Musk", author.getName(),  "Author's name should be Elon Musk");
    assertEquals("works title", entry.getTitle(),   "Entry's title should be Elon Musk");
    assertEquals(newTags, entry.getTags(),  "Entry's tags should be same as newTags");
    assertEquals("works content", entry.getContent(),   "Entry's content should be works content");
    assertEquals(newDate, entry.getDate(),   "Entry's date should be 2025-12-04");
  }

}
