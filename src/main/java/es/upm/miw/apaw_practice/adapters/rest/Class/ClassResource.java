package es.upm.miw.apaw_practice.adapters.rest.Class;


import es.upm.miw.apaw_practice.domain.models.Class.Class;
import es.upm.miw.apaw_practice.domain.services.Class.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(ClassResource.theClass)
public class ClassResource {

    static final String theClass = "/class";
    static final String theList = "/list";

    private final ClassService classService;

    @Autowired
    public ClassResource(ClassService classService){
        this.classService = classService;
    }

    @GetMapping(theList)
    public Stream<Class> readAll() {
        return this.classService.readAll();
    }
}
