package me.a6n.bookmarker;

import dagger.Component;
import me.a6n.bookmarker.bookmarks.BookmarkHandler;

import javax.inject.Singleton;

public class DI {
	@Singleton
	@Component
	interface BookmarkHandlerFactory {
		BookmarkHandler handler();
	}
}
