package es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class MartialArtsGymSeeder {

    private final MembershipRepository membershipRepository;
    private final ClassSessionRepository classSessionRepository;
    private final DojoRepository dojoRepository;
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public MartialArtsGymSeeder(MembershipRepository membershipRepository,
                                ClassSessionRepository classSessionRepository,
                                DojoRepository dojoRepository,
                                EquipmentRepository equipmentRepository) {
        this.membershipRepository = membershipRepository;
        this.classSessionRepository = classSessionRepository;
        this.dojoRepository = dojoRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public void seedDatabase() {
        log.warn("------- MartialArtsGym Initial Load -----------");

        // Create Dojo
        DojoEntity dojo1 = DojoEntity.builder()
                .cadastralReference("DOJO-MAD-001")
                .city("Madrid")
                .foundationDate(LocalDate.of(2010, 5, 12))
                .build();

        DojoEntity dojo2 = DojoEntity.builder()
                .cadastralReference("DOJO-VAL-002")
                .city("Valencia")
                .foundationDate(LocalDate.of(2015, 7, 3))
                .build();

        this.dojoRepository.saveAll(List.of(dojo1, dojo2));

        // Add Equipment
        EquipmentEntity eq1 = EquipmentEntity.builder()
                .barCode(1001)
                .itemLabel("Boxing Gloves")
                .unitCost(new BigDecimal("45.99"))
                .dojo(dojo1)
                .build();

        EquipmentEntity eq2 = EquipmentEntity.builder()
                .barCode(1002)
                .itemLabel("Tatami Mats")
                .unitCost(new BigDecimal("120.00"))
                .dojo(dojo1)
                .build();

        EquipmentEntity eq3 = EquipmentEntity.builder()
                .barCode(1003)
                .itemLabel("Punching Bag")
                .unitCost(new BigDecimal("210.50"))
                .dojo(dojo2)
                .build();

        this.equipmentRepository.saveAll(List.of(eq1, eq2, eq3));

        dojo1.setEquipment(List.of(eq1, eq2));
        dojo2.setEquipment(List.of(eq3));
        this.dojoRepository.saveAll(List.of(dojo1, dojo2));

        // SEssions
        UUID user1Id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001");
        UUID user2Id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002");

        ClassSessionEntity session1 = ClassSessionEntity.builder()
                .referenceCode(501)
                .sessionLength(60)
                .difficultyLevel("Beginner")
                .dojo(dojo1)
                .attendeeIds(List.of(user1Id))
                .build();

        ClassSessionEntity session2 = ClassSessionEntity.builder()
                .referenceCode(502)
                .sessionLength(90)
                .difficultyLevel("Intermediate")
                .dojo(dojo2)
                .attendeeIds(List.of(user1Id, user2Id))
                .build();

        this.classSessionRepository.saveAll(List.of(session1, session2));

        // Add Memberships
        MembershipEntity membership1 = MembershipEntity.builder()
                .id(UUID.fromString("bbbbbbbb-bbbb-cccc-dddd-eeeeffff0001"))
                .monthlyFee(new BigDecimal("39.99"))
                .activationDate(LocalDate.now().minusMonths(3))
                .isCurrentlyActive(true)
                .userId(user1Id)
                .build();

        MembershipEntity membership2 = MembershipEntity.builder()
                .id(UUID.fromString("bbbbbbbb-bbbb-cccc-dddd-eeeeffff0002"))
                .monthlyFee(new BigDecimal("29.99"))
                .activationDate(LocalDate.now().minusMonths(6))
                .isCurrentlyActive(false)
                .userId(user2Id)
                .build();

        this.membershipRepository.saveAll(List.of(membership1, membership2));

        log.warn("------- MartialArtsGym Loaded: 2 dojos, 3 equipment, 2 sessions, 2 memberships -----------");
    }

    public void deleteAll() {
        this.membershipRepository.deleteAll();
        this.classSessionRepository.deleteAll();
        this.equipmentRepository.deleteAll();
        this.dojoRepository.deleteAll();
    }
}
