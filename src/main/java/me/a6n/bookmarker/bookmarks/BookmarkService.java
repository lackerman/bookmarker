package me.a6n.bookmarker.bookmarks;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class BookmarkService {
    private BookmarkRepository repository;

    @Inject
    public BookmarkService(BookmarkRepository repository) {
        this.repository = repository;
    }

    public List<Bookmark> getAll() {
        return repository.all();
    }

    public Optional<Bookmark> findById(String id) {
        return repository.get(id);
    }

    public void add(Bookmark bookmark) {
        repository.add(bookmark);
    }
}
