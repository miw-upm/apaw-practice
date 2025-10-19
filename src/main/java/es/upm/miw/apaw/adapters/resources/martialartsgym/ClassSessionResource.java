package es.upm.miw.apaw.adapters.resources.martialartsgym;

import es.upm.miw.apaw.domain.services.martialartsgym.ClassSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ClassSessionResource.CLASS_SESSIONS)
public class ClassSessionResource {

    public static final String CLASS_SESSIONS = "/class-sessions";
    public static final String REFERENCE_CODE = "/{referenceCode}";

    private final ClassSessionService classSessionService;

    @Autowired
    public ClassSessionResource(ClassSessionService classSessionService) {
        this.classSessionService = classSessionService;
    }

    @DeleteMapping(REFERENCE_CODE)
    public void delete(@PathVariable Integer referenceCode) {
        this.classSessionService.delete(referenceCode);
    }
}
