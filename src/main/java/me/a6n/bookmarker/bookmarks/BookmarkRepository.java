package me.a6n.bookmarker.bookmarks;

import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookmarkRepository {
    private final Map<String, Bookmark> bookmarks;

    @Inject
    public BookmarkRepository() {
        this.bookmarks = new ConcurrentHashMap<>();
        try {
            bookmarks.put("hello", Bookmark.builder()
                    .id("1234")
                    .url(new URL("http://localhost"))
                    .title("Welcome to bookmarker")
                    .description("Blah blah blah")
                    .build());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public List<Bookmark> all() {
        return bookmarks.values().stream()
                .map(b -> Bookmark.builder()
                        .id(b.getId())
                        .description(b.getDescription())
                        .title(b.getTitle())
                        .url(b.getUrl())
                        .build())
                .collect(Collectors.toList());
    }

    public void add(Bookmark bookmark) {
        var id = UUID.randomUUID().toString();
        bookmarks.putIfAbsent(id, Bookmark.builder()
                .id(id)
                .description(bookmark.getDescription())
                .title(bookmark.getTitle())
                .url(bookmark.getUrl())
                .build());
    }

    public Optional<Bookmark> get(String id) {
        return Optional.ofNullable(bookmarks.get(id));
    }

    public void update(String id, Bookmark b) {
        var builder = Bookmark.builder();
        Optional.ofNullable(b.getDescription()).ifPresent(builder::description);
        Optional.ofNullable(b.getTitle()).ifPresent(builder::title);
        Optional.ofNullable(b.getUrl()).ifPresent(builder::url);
        bookmarks.put(id, builder.build());
    }

    public void remove(String id) {
        bookmarks.remove(id);
    }
}
