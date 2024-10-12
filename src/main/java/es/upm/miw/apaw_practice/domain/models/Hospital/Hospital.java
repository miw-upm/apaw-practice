package es.upm.miw.apaw_practice.domain.models.Hospital;


import es.upm.miw.apaw_practice.domain.models.Hospital;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

    @Document
    public class HospitalEntity {

        @Id
        private String id;

        @Indexed(unique = true)
        private String name;
        private String address;
        private Integer capacity;
        private List<DoctorEntity> doctors;
        private List<PatientEntity> patients;

        public HospitalEntity() {
            // Empty constructor for framework
        }

        public HospitalEntity(String id, String name, String address, Integer capacity, List<DoctorEntity> doctors, List<PatientEntity> patients) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.capacity = capacity;
            this.doctors = doctors;
            this.patients = patients;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getCapacity() {
            return capacity;
        }

        public void setCapacity(Integer capacity) {
            this.capacity = capacity;
        }

        public List<DoctorEntity> getDoctors() {
            return doctors;
        }

        public void setDoctors(List<DoctorEntity> doctors) {
            this.doctors = doctors;
        }

        public List<PatientEntity> getPatients() {
            return patients;
        }

        public void setPatients(List<PatientEntity> patients) {
            this.patients = patients;
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "HospitalEntity{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", capacity=" + capacity +
                    ", doctors=" + doctors +
                    ", patients=" + patients +
                    '}';
        }
    }

