package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.domain.services.hospital.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DoctorResource.DOCTORS)
public class DoctorResource {

    static final String DOCTORS = "/hospital/doctors";

    static final String NICK = "/nick";

    private final DoctorService doctorService;

    @Autowired
    public DoctorResource(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping(NICK)
    public String readDoctorNicks(){
        return this.doctorService.readDoctorNick();
    }
    //TODO 2horas
}
