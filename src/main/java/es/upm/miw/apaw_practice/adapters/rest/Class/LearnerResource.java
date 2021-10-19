package es.upm.miw.apaw_practice.adapters.rest.Class;

import es.upm.miw.apaw_practice.domain.models.Class.Learner;
import es.upm.miw.apaw_practice.domain.services.Class.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(LearnerResource.theLearner)
public class LearnerResource {
    static final String theLearner = "/class/learner";

    private final LearnerService learnerService;

    @Autowired
    public LearnerResource(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    @GetMapping
    public Stream<Learner> readAll(){return this.learnerService.readAll();}
}
