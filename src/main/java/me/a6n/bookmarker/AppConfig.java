package me.a6n.bookmarker;

import io.dropwizard.Configuration;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AppConfig extends Configuration {
    public static class Bookmarks {
        @NotNull
        private List<String> tags;

        public List<String> getTags() {
            return tags;
        }
    }

    @NotNull
    private Bookmarks bookmarks;

    public Bookmarks getBookmarks() {
        return bookmarks;
    }
}
