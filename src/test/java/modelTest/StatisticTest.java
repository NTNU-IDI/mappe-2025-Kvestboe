package modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Statistic;
import java.time.LocalDate;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StatisticTest {
  Author author;
  Statistic statistic;


  @BeforeEach
  void setup() {
    author = new Author("Kristian");
    statistic = new Statistic();

  }

  @Test
  void testStatisticGetters() {

    assertEquals(
        new HashMap<Author, Integer>(),
        statistic.getAuthorCount(),
        "Author entry count should be an empty HashMap"
    );
    assertEquals(
        new HashMap<String, Integer>(),
        statistic.getTagCount(),
        "Tag count should be an empty HashMap"
    );
    assertEquals(
        0,
        statistic.getEntriesThisMonth(),
        "Entries this month should be zero"
    );

  }

  @Test
  void testStatisticIncrementers() {
    LocalDate date = LocalDate.now();
    HashMap<Author, Integer> entryCount = new HashMap<>();
    entryCount.put(author, 1);
    HashMap<String, Integer> tagCount = new HashMap<>();
    tagCount.put("test", 1);

    statistic.incrementEntryCount(author);
    statistic.incrementTagCount("test");
    statistic.incrementEntriesThisMonth(date);

    assertEquals(
        entryCount,
        statistic.getAuthorCount(),
        "Entries for author Kristian should be 1"
    );
    assertEquals(
        tagCount,
        statistic.getTagCount(),
        "Tag for author Kristian should be 1"
    );
    assertEquals(
        1,
        statistic.getEntriesThisMonth(),
        "Entries for this month should be 1"
    );

  }

  @Test
  void testStatisticDecrementers() {
    LocalDate date = LocalDate.now();
    HashMap<Author, Integer> entryCount = new HashMap<>();
    entryCount.put(author, 0);
    HashMap<String, Integer> tagCount = new HashMap<>();
    tagCount.put("test", 0);

    statistic.incrementEntryCount(author);
    statistic.incrementTagCount("test");
    statistic.incrementEntriesThisMonth(date);

    statistic.decrementEntryCount(author);
    statistic.decrementTagCount("test");
    statistic.decrementEntriesThisMonth(date);

    assertEquals(
        entryCount,
        statistic.getAuthorCount(),
        "Entries for author Kristian should be 0"
      );
    assertEquals(
        tagCount,
        statistic.getTagCount(),
        "Tag for author Kristian should be 0"
    );
    assertEquals(
        0,
        statistic.getEntriesThisMonth(),
        "Entries for this month should be 0"
    );
  }

}
