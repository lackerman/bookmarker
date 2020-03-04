package me.a6n.bookmarker.bookmarks;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkServiceTest {

    private BookmarkService underTest = new BookmarkService(new BookmarkRepository());

    @Test
    void getBookmarks_ReturnsAllTheBookmarks() {
        underTest.add(
                Bookmark.builder().title("t1").description("d1").build()
        );
        underTest.getAll().forEach(b -> assertAll(
                () -> assertNotNull(b.getId()),
                () -> assertEquals("t1", b.getTitle()),
                () -> assertEquals("d1", b.getDescription())
        ));
    }
}