package me.a6n.bookmarker.bookmarks;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/bookmarks")
public class BookmarkResource {
    private final BookmarkService service;

    @Inject
    public BookmarkResource(BookmarkService service) {
        this.service = service;
    }

    @GET
    @Produces("application/json")
    public List<Bookmark> bookmarks() {
        return service.getAll();
    }
}
