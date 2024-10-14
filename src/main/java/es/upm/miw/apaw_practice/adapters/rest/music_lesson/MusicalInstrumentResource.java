package es.upm.miw.apaw_practice.adapters.rest.music_lesson;

import es.upm.miw.apaw_practice.domain.models.music_lesson.MusicalInstrument;
import es.upm.miw.apaw_practice.domain.services.music_lesson.MusicalInstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MusicalInstrumentResource.MUSICAL_INSTRUMENTS)
public class MusicalInstrumentResource {

  static final String MUSICAL_INSTRUMENTS = "/music-lesson/musical-instruments";

  private final MusicalInstrumentService musicalInstrumentService;

  @Autowired
  public MusicalInstrumentResource(MusicalInstrumentService musicalInstrumentService) {
    this.musicalInstrumentService = musicalInstrumentService;
  }

  @PostMapping
  public MusicalInstrument create(@RequestBody MusicalInstrument musicalInstrument) {
    return this.musicalInstrumentService.create(musicalInstrument);
  }

}
