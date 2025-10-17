package es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.IssueReplyEntity;
import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.RepresentativeEntity;
import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.StudentCouncilEntity;
import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.StudentIssueEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class StudentCouncilSeeder {
    private final StudentIssueRepository studentIssueRepository;
    private final StudentCouncilRepository studentCouncilRepository;
    private final RepresentativeRepository representativeRepository;
    private final IssueReplyRepository issueReplyRepository;

    @Autowired
    public StudentCouncilSeeder(StudentIssueRepository studentIssueRepository,
                                StudentCouncilRepository studentCouncilRepository,
                                RepresentativeRepository representativeRepository,
                                IssueReplyRepository issueReplyRepository) {
        this.studentIssueRepository = studentIssueRepository;
        this.studentCouncilRepository = studentCouncilRepository;
        this.representativeRepository = representativeRepository;
        this.issueReplyRepository = issueReplyRepository;
    }

    public void seedDatabase() {
        log.warn("------- StudentCouncil Initial Load -----------");

        // ===== Issue replies =====
        IssueReplyEntity reply1 = IssueReplyEntity.builder()
                .id(UUID.fromString("bbbbbbbb-bbbb-cccc-dddd-eeeeffff0000"))
                .reason("Reply1")
                .createDate(LocalDateTime.now().minusDays(3))
                .compensation(new BigDecimal("0.00"))
                .build();

        IssueReplyEntity reply2 = IssueReplyEntity.builder()
                .id(UUID.fromString("bbbbbbbb-bbbb-cccc-dddd-eeeeffff0001"))
                .reason("Reply2")
                .createDate(LocalDateTime.now().minusDays(1))
                .compensation(new BigDecimal("200.00"))
                .build();

        this.issueReplyRepository.saveAll(Arrays.asList(reply1, reply2));

        // ===== Student issues =====
        StudentIssueEntity issue1 = StudentIssueEntity.builder()
                .id(UUID.fromString("cccccccc-bbbb-cccc-dddd-eeeeffff0000"))
                .statement("Problem1")
                .reportDate(LocalDateTime.now().minusDays(10))
                .closed(false)
                .urgency(2)
                .replies(Collections.singletonList(reply1))
                .build();

        StudentIssueEntity issue2 = StudentIssueEntity.builder()
                .id(UUID.fromString("cccccccc-bbbb-cccc-dddd-eeeeffff0001"))
                .statement("Problem2")
                .reportDate(LocalDateTime.now().minusDays(5))
                .closed(true)
                .urgency(1)
                .replies(Collections.singletonList(reply2))
                .build();

        this.studentIssueRepository.saveAll(Arrays.asList(issue1, issue2));

        // ===== Representatives =====
        RepresentativeEntity rep1 = RepresentativeEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .joinDate(LocalDateTime.now().minusYears(1))
                .responsibility("President")
                .representativeId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .topics(Arrays.asList(issue1, issue2))
                .build();

        RepresentativeEntity rep2 = RepresentativeEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                .joinDate(LocalDateTime.now().minusMonths(6))
                .responsibility("Treasurer")
                .representativeId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                .topics(Collections.singletonList(issue1))
                .build();

        RepresentativeEntity rep3 = RepresentativeEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                .joinDate(LocalDateTime.now().minusMonths(6))
                .responsibility("Treasurer2")
                .representativeId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                .topics(Collections.singletonList(issue2))
                .build();

        this.representativeRepository.saveAll(Arrays.asList(rep1, rep2, rep3));

        // ===== Councils =====
        StudentCouncilEntity council1 = StudentCouncilEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .council("Council1")
                .site("Building A")
                .resources(new BigDecimal("50000.00"))
                .representatives(Arrays.asList(rep1, rep2))
                .build();

        StudentCouncilEntity council2 = StudentCouncilEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                .council("Council2")
                .site("Building B")
                .resources(new BigDecimal("30000.00"))
                .representatives(Collections.singletonList(rep1))
                .build();

        StudentCouncilEntity council3 = StudentCouncilEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                .council("Council3")
                .site("Building C")
                .resources(new BigDecimal("10000.00"))
                .representatives(Collections.singletonList(rep3))
                .build();

        this.studentCouncilRepository.saveAll(Arrays.asList(council1, council2, council3));

        log.warn("------- StudentCouncil Loaded: 3 councils -----------");
    }


    public void deleteAll() {
        this.studentCouncilRepository.deleteAll();
        this.representativeRepository.deleteAll();
        this.studentIssueRepository.deleteAll();
        this.issueReplyRepository.deleteAll();
    }
}
