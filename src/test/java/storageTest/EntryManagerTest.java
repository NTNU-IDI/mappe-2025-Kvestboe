package storageTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.iir.bidata.model.Author;
import edu.ntnu.iir.bidata.model.Entry;
import edu.ntnu.iir.bidata.model.Statistic;
import edu.ntnu.iir.bidata.storage.EntryManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntryManagerTest {

  Statistic stat;
  EntryManager entryManager;
  Entry entry;

  @BeforeEach
  void setUp() {
    stat = new Statistic();
    entryManager = new EntryManager(stat);

    Author author = new Author("Kristian");
    entry = new Entry(author, "test title", new ArrayList<>(List.of("test1", "test2")),
        "test content");

  }

  @Test
  void testAddEntryStoresEntry() {
    int key = entryManager.addEntry(entry);

    assertEquals(entry, entryManager.getEntry(key), "The entries should be the same");
    assertEquals(1, entryManager.getDiary().size(),
        "The entry should only have size of 1 after storing one entry");

    // assertTrue learned from ai
    assertTrue(entryManager.getDiary().containsKey(0), "The entry should have the key value");

  }

  @Test
  void testDeleteEntryRemovesEntry() {
    int key = entryManager.addEntry(entry);
    assertEquals(entry, entryManager.getEntry(key), "The entries should be the same");

    entryManager.deleteEntry(entry);

    // methods under recommended by intellij
    assertNull(entryManager.getEntry(key), "The entry should be null, since it's empty");
    assertFalse(entryManager.getDiary().containsKey(key), "The entry should have the key value");
    assertEquals(0, entryManager.getDiary().size(), "The entry should have size 0");
  }

  @Test
  void testUpdateEntryReplacesEntry() {
    entryManager.addEntry(entry);
    Author newAuthor = new Author("Obama");
    Entry newEntry = new Entry(newAuthor, "test", new ArrayList<>(List.of("test1", "test2")),
        "test content");

    entryManager.updateEntry(0, newEntry);

    assertEquals(newAuthor, entryManager.getEntry(0).getAuthor(), "The entries should have same author");
    assertEquals(1,  entryManager.getDiary().size(), "The size should be 1");
    assertTrue(entryManager.getDiary().containsValue(newEntry), "The diary should have the new entry");
    assertFalse(entryManager.getDiary().containsValue(entry), "The diary should not have the old entry");

  }

  @Test
  void testGetDiaryReturnsCopy() {
    int key = entryManager.addEntry(entry);
    HashMap<Integer, Entry> diaryCopy = entryManager.getDiary();

    assertEquals(entry, diaryCopy.get(key), "The entries should be the same, since it's a copy");
    // recommended by intelliJ, original assertTrue(diaryCopy != entryManager.getDiary())
    assertNotSame(diaryCopy, entryManager.getDiary(), "The diary should not be the same");

    diaryCopy.clear();
    assertFalse(entryManager.getDiary().isEmpty(), "The original diary should not be empty");
  }

// Old tests replaced by more meaningful tests.

//  void testDifferentSortMethods() {
//    entryManager.addEntry(entry1);
//    entryManager.addEntry(entry2);
//    entryManager.addEntry(entry3);
//
//    HashMap<Integer, Entry> entryManagerDummy = new HashMap<>();
//    entryManagerDummy.put(0, entry1);
//    entryManagerDummy.put(1, entry2);
//
//    assertEquals(
//        entryManagerDummy,
//        entryManager.searchTitle("title"),
//        "The entry manager should return the entries with 'title' in their title."
//    );
//
//    assertEquals(
//        entryManagerDummy,
//        entryManager.searchAuthor(author1),
//        "The entry manager should return the entries with 'author' as their author."
//    );
//
//    entryManagerDummy.put(2, entry3);
//
//    assertEquals(
//        entryManagerDummy,
//        entryManager.searchTag("test1"),
//        "The entry manager should return the entries with 'test1' in their tag."
//    );
//
//    LocalDate date1 = LocalDate.of(2025, 12, 2);
//    LocalDate date2 = LocalDate.of(2025, 12, 3);
//    entryManager.getEntry(0).setDate(date1);
//    entryManager.getEntry(1).setDate(date2);
//
//    entryManagerDummy.clear();
//    entryManagerDummy.put(0, entry1);
//    assertEquals(
//        entryManagerDummy,
//        entryManager.searchDate(date1),
//        "The entry manager should only have the first entry, key 0."
//    );
//
//    entryManagerDummy.put(1, entry2);
//    assertEquals(
//        entryManagerDummy,
//        entryManager.searchPeriod(
//            LocalDate.of(2025, 12, 1),
//            LocalDate.of(2025, 12, 4)
//        ),
//        "The entry manager should have the first and second entry."
//    );
//  }


}
