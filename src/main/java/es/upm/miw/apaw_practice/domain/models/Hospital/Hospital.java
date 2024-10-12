package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.util.List;

public class Hospital {
package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.util.List;

    public class Hospital {
        private String name;
        private String address;  // Changed from 'location' to 'address'
        private int capacity;
        private List<Doctor> doctors;
        private List<Patient> patients;

        public Hospital() {
            // Empty constructor for framework
        }

        public Hospital(String name, String address, int capacity, List<Doctor> doctors, List<Patient> patients) {
            this.name = name;
            this.address = address;  // Updated here
            this.capacity = capacity;
            this.doctors = doctors;
            this.patients = patients;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {  // Updated getter
            return address;
        }

        public void setAddress(String address) {  // Updated setter
            this.address = address;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public List<Doctor> getDoctors() {
            return doctors;
        }

        public void setDoctors(List<Doctor> doctors) {
            this.doctors = doctors;
        }

        public List<Patient> getPatients() {
            return patients;
        }

        public void setPatients(List<Patient> patients) {
            this.patients = patients;
        }

        @Override
        public String toString() {
            return "Hospital{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +  // Updated to 'address'
                    ", capacity=" + capacity +
                    ", doctors=" + doctors +
                    ", patients=" + patients +
                    '}';
        }
    }
package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
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
        private String address;  // Ensure it is 'address' and not 'location'
        private Integer capacity;
        private List<DoctorEntity> doctors;
        private List<PatientEntity> patients;

        public HospitalEntity() {
            // Empty constructor for framework
        }

        public HospitalEntity(String name, String address, Integer capacity, List<DoctorEntity> doctors) {
            this.name = name;
            this.address = address;
            this.capacity = capacity;
            this.doctors = doctors;
            this.patients = null;
        }

        public HospitalEntity(Hospital hospital) {
            this.id = UUID.randomUUID().toString();
            this.name = hospital.getName();
            this.address = hospital.getAddress();  // Updated from 'getLocation()' to 'getAddress()'
            this.capacity = hospital.getCapacity();
            this.doctors = hospital.getDoctors().stream()
                    .map(Doctor::toDoctorEntity)
                    .collect(Collectors.toList());
            this.patients = hospital.getPatients().stream()
                    .map(Patient::toPatientEntity)
                    .collect(Collectors.toList());
        }

        public Hospital toHospital() {
            Hospital hospital = new Hospital();
            hospital.setName(this.name);
            hospital.setAddress(this.address);
            hospital.setCapacity(this.capacity);
            hospital.setDoctors(this.doctors.stream()
                    .map(DoctorEntity::toDoctor)
                    .collect(Collectors.toList()));
            hospital.setPatients(this.patients.stream()
                    .map(PatientEntity::toPatient)
                    .collect(Collectors.toList()));
            return hospital;
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
    }
}
