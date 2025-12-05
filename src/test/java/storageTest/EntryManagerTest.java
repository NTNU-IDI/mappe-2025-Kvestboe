package storageTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntryManagerTest {
  Entry entry1;
  Entry entry2;
  Entry entry3;

  Author author1;
  Author author2;

  EntryManager entryManager;

  @BeforeEach
  void setup() {
    author1 = new Author("Kristian");
    author2 = new Author("Obama");

    entry1 = new Entry(
        author1,
        "test title",
        new ArrayList<>(List.of("test1", "test2")),
        "test content"
    );
    entry2 = new Entry(
        author1,
        "test title",
        new ArrayList<>(List.of("test1", "test2")),
        "test content"
    );
    entry3 = new Entry(
        author2,
        "test",
        new ArrayList<>(List.of("test1", "test2")),
        "test content"
    );

    entryManager = new EntryManager();
  }

  @Test
  void testAddEntryToEntryManager() {
    entryManager.addEntry(entry1);
    entryManager.addEntry(entry2);
    entryManager.addEntry(entry3);

    HashMap<Integer, Entry> entryManagerDummy = new HashMap<>();
    entryManagerDummy.put(0, entry1);
    entryManagerDummy.put(1, entry2);
    entryManagerDummy.put(2, entry3);

    assertEquals(
        entryManagerDummy,
        entryManager.getEntries(),
        "Entry manager dummy should be the same as the actual one."
    );
  }

  @Test
  void testDifferentSortMethods() {
    entryManager.addEntry(entry1);
    entryManager.addEntry(entry2);
    entryManager.addEntry(entry3);

    HashMap<Integer, Entry> entryManagerDummy = new HashMap<>();
    entryManagerDummy.put(0, entry1);
    entryManagerDummy.put(1, entry2);

    assertEquals(
        entryManagerDummy,
        entryManager.searchTitle("title"),
        "The entry manager should return the entries with 'title' in their title."
    );

    assertEquals(
        entryManagerDummy,
        entryManager.searchAuthor(author1),
        "The entry manager should return the entries with 'author' as their author."
    );

    entryManagerDummy.put(2, entry3);

    assertEquals(
        entryManagerDummy,
        entryManager.searchTag("test1"),
        "The entry manager should return the entries with 'test1' in their tag."
    );

    LocalDate date1 = LocalDate.of(2025, 12, 2);
    LocalDate date2 = LocalDate.of(2025, 12, 3);
    entryManager.getEntry(0).setDate(date1);
    entryManager.getEntry(1).setDate(date2);

    entryManagerDummy.clear();
    entryManagerDummy.put(0, entry1);
    assertEquals(
        entryManagerDummy,
        entryManager.searchDate(date1),
        "The entry manager should only have the first entry, key 0."
    );

    entryManagerDummy.put(1, entry2);
    assertEquals(
        entryManagerDummy,
        entryManager.searchPeriod(
            LocalDate.of(2025, 12, 1),
            LocalDate.of(2025, 12, 4)
        ),
        "The entry manager should have the first and second entry."
    );
  }

  @Test
  void testDeleteMethod() {
    entryManager.addEntry(entry1);
    entryManager.addEntry(entry2);

    HashMap<Integer, Entry> entryManagerDummy = new HashMap<>();
    entryManagerDummy.put(0, entry1);

    entryManager.deleteEntry(entry2);

    assertEquals(
        entryManagerDummy,
        entryManager.getEntries(),
        "Entry manager dummy should be the same as the actual one."
    );
  }

}
