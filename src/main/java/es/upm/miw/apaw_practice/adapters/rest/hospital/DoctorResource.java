package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.hospital.Doctor;
import es.upm.miw.apaw_practice.domain.services.hospital.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(DoctorResource.DOCTORS)
public class DoctorResource {

    static final String DOCTORS = "/hospital/doctors";
    static final String NICK = "/nick";
    static final String SEARCH = "/search";

    private final DoctorService doctorService;

    @Autowired
    public DoctorResource(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping(NICK)
    public Stream<Doctor> readDoctorNicks(){
        return this.doctorService.readDoctorNicks();
    }

    @GetMapping(SEARCH)
    public Stream<Doctor> findSurnamesByDiseaseSeverity(@RequestParam String q){
        Boolean severity = new LexicalAnalyzer().extractWithAssure(q,"diseaseSeverity", Boolean::valueOf);
        return this.doctorService.findSurnamesByDiseaseSeverity(severity);
    }
}
