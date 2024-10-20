package es.upm.miw.apaw_practice.adapters.rest.music_lesson;

import java.util.stream.Stream;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Branch;
import es.upm.miw.apaw_practice.domain.services.music_lesson.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BranchResource.BRANCHES)
public class BranchResource {

  static final String BRANCHES = "/music-lesson/branches";

  static final String SEARCH = "/search";

  static final String UNIQUE_MODELS_BY_ADDRESS = "/unique-models-by-address";

  private final BranchService branchService;

  @Autowired
  public BranchResource(BranchService branchService) {
    this.branchService = branchService;
  }

  @GetMapping(SEARCH)
  public Stream<Branch> findByAddress(@RequestParam String q) {
    String address = new LexicalAnalyzer().extractWithAssure(q, "address");
    return this.branchService.findByAddress(address);
  }

  @GetMapping(SEARCH + UNIQUE_MODELS_BY_ADDRESS)
  public Stream<String> findUniqueMusicalInstrumentModelsByAddress(@RequestParam String q) {
    String address = new LexicalAnalyzer().extractWithAssure(q, "address");
    return this.branchService.findUniqueMusicalInstrumentModelsByAddress(address);
  }

}
