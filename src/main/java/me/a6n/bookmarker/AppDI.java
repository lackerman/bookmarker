package me.a6n.bookmarker;

import dagger.Component;
import me.a6n.bookmarker.bookmarks.BookmarkResource;
import javax.inject.Singleton;

@Singleton
@Component
public interface AppDI {
    BookmarkResource bookmarkResource();
}
