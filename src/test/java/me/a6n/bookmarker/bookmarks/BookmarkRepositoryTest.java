package me.a6n.bookmarker.bookmarks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BookmarkRepositoryTest {
    @Test
    @DisplayName("Repository has an existing entry")
    public void testRepositorySize() {
        BookmarkRepository repository = new BookmarkRepository();
        assertEquals(1, repository.all().size());
    }
}
