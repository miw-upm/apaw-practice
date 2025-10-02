package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.PositionEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.MeetingEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.AttendeeEntity;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class RecruitingSeeder {

    private final PositionRepository positionRepository;
    private final ApplicationRepository applicationRepository;
    private final MeetingRepository meetingRepository;
    private final AttendeeRepository attendeeRepository;

    @Autowired
    public RecruitingSeeder(PositionRepository positionRepository, ApplicationRepository applicationRepository, MeetingRepository meetingRepository, AttendeeRepository attendeeRepository) {
        this.positionRepository = positionRepository;
        this.applicationRepository = applicationRepository;
        this.meetingRepository = meetingRepository;
        this.attendeeRepository = attendeeRepository;
    }

    public void seedDatabase() {
        log.warn("------- Recruiting Initial Load -----------");

        // 1) Position
        PositionEntity[] positions = {
                PositionEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .name("ABAP developer")
                        .description("ABAP developer for HR. At least 5 years of experience in OO and payroll implementation.")
                        .annualSalary(new BigDecimal("52000.00"))
                        .bonusSalary(new BigDecimal("5200.00"))
                        .numVacancies(3)
                        .build(),
                PositionEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .name("CPI consultant")
                        .description("Integration Services consultant. At least 3 year of experiences in CPI development and configuration.")
                        .annualSalary(new BigDecimal("48000.00"))
                        .bonusSalary(new BigDecimal("4800.00"))
                        .numVacancies(3)
                        .build(),
                PositionEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .name("Integration Consultant for HCM")
                        .description("End-to-end consultant for integrations with SAP HCM. At least 8 years of experience in SAP HCM projects.")
                        .annualSalary(new BigDecimal("56000.00"))
                        .bonusSalary(new BigDecimal("6400.00"))
                        .numVacancies(2)
                        .build(),
                PositionEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .name("Technical Lead for SAP HCM")
                        .description("Lead in implementation for SAP Successfactors and SAP HCM. At least 10 years of experience as technical Leader.")
                        .annualSalary(new BigDecimal("68000.00"))
                        .bonusSalary(new BigDecimal("8000.00"))
                        .numVacancies(1)
                        .build()
        };
        this.positionRepository.saveAll(Arrays.asList(positions));

        // 2) Attendee
        AttendeeEntity[] attendees = {
                AttendeeEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010"))
                        .lastName("Markus")
                        .lastName("Urbanietz")
                        .emailAddress("markus.urbanietz@test.com")
                        .build(),
                AttendeeEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0011"))
                        .firstName("Beate")
                        .lastName("Magnie")
                        .emailAddress("beate.magnie@test.com")
                        .build(),
                AttendeeEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0012"))
                        .firstName("Javier")
                        .lastName("Oliver")
                        .emailAddress("javier.oliver@test.com")
                        .build(),
                AttendeeEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0013"))
                        .firstName("Karolyn")
                        .lastName("Sanz")
                        .emailAddress("karolyn.sanz@test.com")
                        .build(),
                AttendeeEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0014"))
                        .firstName("Felix")
                        .lastName("Issle")
                        .emailAddress("felix.issle@test.com")
                        .build(),
                AttendeeEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0015"))
                        .firstName("Andrea")
                        .lastName("Schulz")
                        .emailAddress("andrea.schulz@test.com")
                        .build(),
        };
        this.attendeeRepository.saveAll(Arrays.asList(attendees));

        /* 3) Meeting */
        MeetingEntity[] meetings = new MeetingEntity[]{
                MeetingEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020"))
                        .date(LocalDateTime.of(2025, 10, 6, 14, 30, 0))
                        .url("url-for-meeting-1")
                        .attendees(Arrays.asList(attendees[0], attendees[1], attendees[3]))
                        .build(),
                MeetingEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0021"))
                        .date(LocalDateTime.of(2025, 10, 10, 11, 30, 0))
                        .url("url-for-meeting-2")
                        .attendees(Arrays.asList(attendees[1], attendees[2], attendees[4]))
                        .build(),
                MeetingEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0022"))
                        .date(LocalDateTime.of(2025, 10, 9, 10, 0, 0))
                        .url("url-for-meeting-3")
                        .attendees(Arrays.asList(attendees[0], attendees[5]))
                        .build(),
                MeetingEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0023"))
                        .date(LocalDateTime.of(2025, 10, 14, 9, 0, 0))
                        .url("url-for-meeting-4")
                        .attendees(Collections.singletonList(attendees[1]))
                        .build(),
                MeetingEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0024"))
                        .date(LocalDateTime.of(2025, 10, 9, 15, 15, 0))
                        .url("url-for-meeting-5")
                        .attendees(Arrays.asList(attendees[3], attendees[4]))
                        .build(),
                MeetingEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0025"))
                        .date(LocalDateTime.of(2025, 10, 13, 12, 15, 0))
                        .url("url-for-meeting-6")
                        .attendees(Arrays.asList(attendees[2], attendees[5]))
                        .build(),
                MeetingEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0026"))
                        .date(LocalDateTime.of(2025, 10, 15, 12, 30, 0))
                        .url("//url-for-meeting-7")
                        .attendees(Collections.singletonList(attendees[0]))
                        .build()
        };
        this.meetingRepository.saveAll(Arrays.asList(meetings));

        // 4) Application
        ApplicationEntity[] applications = {
                ApplicationEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0030"))
                        .status(Status.Open)
                        .date(LocalDate.now())
                        .referral(true)
                        .position(positions[0].getId())
                        .meetingList(Arrays.asList(meetings[0],meetings[1]))
                        .build(),
                ApplicationEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0031"))
                        .status(Status.In_process)
                        .date(LocalDate.now().minusDays(5))
                        .referral(false)
                        .position(positions[1].getId())
                        .meetingList(Collections.singletonList(meetings[2]))
                        .build(),
                ApplicationEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0032"))
                        .status(Status.Hired)
                        .date(LocalDate.now().minusDays(4))
                        .referral(false)
                        .position(positions[2].getId())
                        .meetingList(Arrays.asList(meetings[3],meetings[4],meetings[5]))
                        .build(),
                ApplicationEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0033"))
                        .status(Status.Rejected)
                        .date(LocalDate.now().minusDays(7))
                        .referral(false)
                        .position(positions[3].getId())
                        .meetingList(Collections.singletonList(meetings[6]))
                        .build()
        };
        this.applicationRepository.saveAll(Arrays.asList(applications));

        log.warn("        ------- Recruiting");
    }


    public void deleteAll() {
        this.applicationRepository.deleteAll();
        this.meetingRepository.deleteAll();
        this.attendeeRepository.deleteAll();
        this.positionRepository.deleteAll();
    }
}
