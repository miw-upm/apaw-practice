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
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class RecruitingSeeder {

    private final PositionRepository positionRepository;
    private final ApplicationRepository applicationRepository;
    private final AttendeeRepository attendeeRepository;

    @Autowired
    public RecruitingSeeder(PositionRepository positionRepository, ApplicationRepository applicationRepository, AttendeeRepository attendeeRepository) {
        this.positionRepository = positionRepository;
        this.applicationRepository = applicationRepository;
        this.attendeeRepository = attendeeRepository;
    }

    public void seedDatabase() {
        log.warn("------- Recruiting Initial Load -----------");

        // 1) Position
        PositionEntity[] positions = {
                PositionEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
                        .reference(1001)
                        .name("ABAP developer")
                        .description("ABAP developer for HR. At least 5 years of experience in OO and payroll implementation.")
                        .annualSalary(new BigDecimal("52000.00"))
                        .bonusSalary(new BigDecimal("5200.00"))
                        .numVacancies(3)
                        .build(),
                PositionEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1001"))
                        .reference(1002)
                        .name("CPI consultant")
                        .description("Integration Services consultant. At least 3 year of experiences in CPI development and configuration.")
                        .annualSalary(new BigDecimal("48000.00"))
                        .bonusSalary(new BigDecimal("4800.00"))
                        .numVacancies(3)
                        .build(),
                PositionEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1002"))
                        .reference(1003)
                        .name("Integration Consultant for HCM")
                        .description("End-to-end consultant for integrations with SAP HCM. At least 8 years of experience in SAP HCM projects.")
                        .annualSalary(new BigDecimal("56000.00"))
                        .bonusSalary(new BigDecimal("6400.00"))
                        .numVacancies(2)
                        .build(),
                PositionEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1003"))
                        .reference(1004)
                        .name("Technical Lead for SAP HCM")
                        .description("Lead in implementation for SAP Successfactors and SAP HCM. At least 10 years of experience as technical Leader.")
                        .annualSalary(new BigDecimal("68000.00"))
                        .bonusSalary(new BigDecimal("8000.00"))
                        .numVacancies(1)
                        .build(),
                PositionEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1004"))
                        .reference(1005)
                        .name("HCM Manager")
                        .description("Manager for HCM department. At least 15 years of experience in the sector.")
                        .annualSalary(new BigDecimal("87000.00"))
                        .bonusSalary(new BigDecimal("25000.00"))
                        .numVacancies(1)
                        .build()
        };
        this.positionRepository.saveAll(Arrays.asList(positions));

        // 2) Attendee
        AttendeeEntity[] attendees = {
                AttendeeEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010"))
                        .fullName("Markus Urbanietz")
                        .phoneNumber("+4112345123")
                        .emailAddress("markus.urbanietz@test.com")
                        .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .build(),
                AttendeeEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0011"))
                        .fullName("Beate Magnie")
                        .phoneNumber("+4143645789")
                        .emailAddress("beate.magnie@test.com")
                        .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005"))
                        .build(),
                AttendeeEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0012"))
                        .fullName("Javier Oliver")
                        .phoneNumber("+3443445689")
                        .emailAddress("javier.oliver@test.com")
                        .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .build(),
                AttendeeEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0013"))
                        .fullName("Karolyn Sanz")
                        .phoneNumber("+346588879")
                        .emailAddress("karolyn.sanz@test.com")
                        .build(),
                AttendeeEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0014"))
                        .fullName("Felix Issle")
                        .phoneNumber("+4173912799")
                        .emailAddress("felix.issle@test.com")
                        .build(),
                AttendeeEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0015"))
                        .fullName("Andrea Schulz")
                        .phoneNumber("+4165889667")
                        .emailAddress("andrea.schulz@test.com")
                        .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005"))
                        .build(),
        };
        this.attendeeRepository.saveAll(Arrays.asList(attendees));

        // 3) Application
        ApplicationEntity[] applications = {
                ApplicationEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0030"))
                        .status(Status.Open)
                        .created(LocalDate.now())
                        .referral(true)
                        .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .positionEntity(positions[0])
                        .meetingList(Arrays.asList(
                                MeetingEntity.builder()
                                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020"))
                                        .date(LocalDateTime.of(2025, 10, 6, 14, 30, 0))
                                        .url("//url-for-meeting-1")
                                        .attendees(Arrays.asList(attendees[0], attendees[1], attendees[5]))
                                        .build(),
                                MeetingEntity.builder()
                                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0021"))
                                        .date(LocalDateTime.of(2025, 10, 10, 11, 30, 0))
                                        .url("//url-for-meeting-2")
                                        .attendees(Arrays.asList(attendees[1], attendees[2], attendees[4]))
                                        .build(),
                                MeetingEntity.builder()
                                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0022"))
                                        .date(LocalDateTime.of(2025, 10, 9, 10, 0, 0))
                                        .url("//url-for-meeting-3")
                                        .attendees(Arrays.asList(attendees[0], attendees[5]))
                                        .build()
                        )).build(),
                ApplicationEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0031"))
                        .status(Status.In_process)
                        .created(LocalDate.now().minusDays(5))
                        .referral(false)
                        .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .positionEntity(positions[1])
                        .meetingList(Arrays.asList(
                                MeetingEntity.builder()
                                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0023"))
                                        .date(LocalDateTime.of(2025, 10, 14, 9, 0, 0))
                                        .url("//url-for-meeting-4")
                                        .attendees(Collections.singletonList(attendees[1]))
                                        .build(),
                                MeetingEntity.builder()
                                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0024"))
                                        .date(LocalDateTime.of(2025, 10, 9, 15, 15, 0))
                                        .url("//url-for-meeting-5")
                                        .attendees(Arrays.asList(attendees[1], attendees[4]))
                                        .build(),
                                MeetingEntity.builder()
                                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0025"))
                                        .date(LocalDateTime.of(2025, 10, 13, 12, 15, 0))
                                        .url("//url-for-meeting-6")
                                        .attendees(Arrays.asList(attendees[2], attendees[5]))
                                        .build()
                        ))
                        .build(),
                ApplicationEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0032"))
                        .status(Status.Hired)
                        .created(LocalDate.now().minusDays(4))
                        .referral(false)
                        .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .positionEntity(positions[2])
                        .meetingList(Arrays.asList(
                                MeetingEntity.builder()
                                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0025"))
                                        .date(LocalDateTime.of(2025, 10, 13, 12, 15, 0))
                                        .url("//url-for-meeting-7")
                                        .attendees(Arrays.asList(attendees[2], attendees[5]))
                                        .build(),
                                MeetingEntity.builder()
                                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0026"))
                                        .date(LocalDateTime.of(2025, 10, 15, 12, 30, 0))
                                        .url("//url-for-meeting-8")
                                        .attendees(Collections.singletonList(attendees[0]))
                                        .build()
                        ))
                        .build(),
                ApplicationEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0033"))
                        .status(Status.In_process)
                        .created(LocalDate.now().minusDays(7))
                        .referral(false)
                        .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .positionEntity(positions[3])
                        .meetingList(Arrays.asList(
                                MeetingEntity.builder()
                                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0027"))
                                        .date(LocalDateTime.of(2025, 10, 16, 10, 0, 0))
                                        .url("//url-for-meeting-9")
                                        .attendees(Arrays.asList(attendees[2], attendees[5]))
                                        .build(),
                                MeetingEntity.builder()
                                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0028"))
                                        .date(LocalDateTime.of(2025, 10, 25, 9, 0, 0))
                                        .url("//url-for-meeting-10")
                                        .attendees(Collections.singletonList(attendees[0]))
                                        .build()
                        ))
                        .build(),
                ApplicationEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0034"))
                        .status(Status.Rejected)
                        .created(LocalDate.now().minusDays(7))
                        .referral(false)
                        .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                        .positionEntity(positions[4])
                        .meetingList(List.of())
                        .build()
        };
        this.applicationRepository.saveAll(Arrays.asList(applications));

        log.warn("        ------- Recruiting");
    }

    public void deleteAll() {
        this.applicationRepository.deleteAll();
        this.attendeeRepository.deleteAll();
        this.positionRepository.deleteAll();
    }
}