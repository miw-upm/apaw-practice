package es.upm.miw.apaw.adapters.resources.videoWebsite;

import es.upm.miw.apaw.domain.services.videoWebsite.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.upm.miw.apaw.domain.models.videoWebsite.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(CommentResource.COMMENTS)
public class CommentResource {

    public static final String COMMENTS = "/videoWebsite/comments";

    private final CommentService commentService;

    @Autowired
    public CommentResource(CommentService commentService) {
        this.commentService = commentService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
        this.commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public Comment create(@Valid @RequestBody Comment comment) {
        return this.commentService.create(comment);
    }

    @GetMapping("/video/{title}/mobiles")
    public List<String> getCommentersMobileByVideoTitle(@PathVariable String title) {
        return this.commentService.findCommentersMobileByVideoTitle(title);

    }
}
