package es.upm.miw.apaw.adapters.mongodb.fighters.daos;

import es.upm.miw.apaw.adapters.mongodb.fighters.entities.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class FightersSeeder {
    private final FighterRepository fighterRepository;
    private final CoachRepository coachRepository;
    private final MartialArtRepository martialArtRepository;

    @Autowired
    public FightersSeeder(FighterRepository fighterRepository, CoachRepository coachRepository, MartialArtRepository martialArtRepository) {
        this.fighterRepository = fighterRepository;
        this.coachRepository = coachRepository;
        this.martialArtRepository = martialArtRepository;
    }

    public void seedDatabase() {
        log.warn("------- Fighters Initial Load -----------");

        // ==== COACHES ====
        CoachEntity coach1 = CoachEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .fullName("John Smith")
                .academy("Nova Gym")
                .experienceYears(12)
                .build();

        CoachEntity coach2 = CoachEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                .fullName("Carlos Mendes")
                .academy("Gracie Team")
                .experienceYears(20)
                .build();

        CoachEntity coach3 = CoachEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                .fullName("Yuki Tanaka")
                .academy("Tokyo Dojo")
                .experienceYears(15)
                .build();

        CoachEntity coach4 = CoachEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                .fullName("Mike Thompson")
                .academy("Iron Fist Gym")
                .experienceYears(0)
                .build();

        CoachEntity coach5 = CoachEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))
                .fullName("Elena Petrova")
                .academy("Moscow Combat Club")
                .experienceYears(8)
                .build();

        this.coachRepository.saveAll(List.of(coach1, coach2, coach3, coach4, coach5));

        // ==== MARTIAL ARTS ====
        MartialArtEntity mma = MartialArtEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2000"))
                .discipline("MMA")
                .origin("USA")
                .description("Mixed Martial Arts. You can use all martial arts disciplines")
                .striking(true)
                .grappling(true)
                .build();

        MartialArtEntity muayThai = MartialArtEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2001"))
                .discipline("Muay Thai")
                .origin("Thailand")
                .description("Thai kickboxing. The art of the eight limbs")
                .striking(true)
                .grappling(false)
                .build();

        String brazil = "Brazil";
        MartialArtEntity bjj = MartialArtEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2002"))
                .discipline("BJJ")
                .origin(brazil)
                .description("Brazilian Jiu-Jitsu")
                .striking(false)
                .grappling(true)
                .build();

        MartialArtEntity boxing = MartialArtEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2003"))
                .discipline("Boxing")
                .origin("England")
                .description("The sweet science of punching and footwork")
                .striking(true)
                .grappling(false)
                .build();

        MartialArtEntity taekwondo = MartialArtEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2004"))
                .discipline("Taekwondo")
                .origin("Korea")
                .description("Korean martial art focusing on high kicks and fast movements")
                .striking(true)
                .grappling(false)
                .build();

        MartialArtEntity kravMaga = MartialArtEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2005"))
                .discipline("Krav Maga")
                .origin("Israel")
                .description("Self-defense system focused on real-world situations")
                .striking(true)
                .grappling(true)
                .build();

        MartialArtEntity karate = MartialArtEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2006"))
                .discipline("Karate")
                .origin("Japan")
                .description("Japanese martial art emphasizing punches, kicks, and discipline")
                .striking(true)
                .grappling(false)
                .build();

        MartialArtEntity k1 = MartialArtEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2007"))
                .discipline("K1")
                .origin("Japan")
                .description("Kickboxing style combining karate, Muay Thai, and boxing techniques")
                .striking(true)
                .grappling(false)
                .build();

        this.martialArtRepository.saveAll(List.of(mma, muayThai, bjj, boxing, taekwondo, kravMaga, karate, k1));

        // ==== RATINGS ====
        RatingEntity rating1 = RatingEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"))
                .score(5)
                .comment("Excellent fighter!")
                .createdAt(LocalDateTime.now())
                .userId(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
                .build();

        RatingEntity rating2 = RatingEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0101"))
                .score(4)
                .comment("Strong performance")
                .createdAt(LocalDateTime.now().minusDays(2))
                .userId(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1001"))
                .build();

        RatingEntity rating3 = RatingEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0102"))
                .score(3)
                .comment("Needs better cardio")
                .createdAt(LocalDateTime.now().minusDays(5))
                .userId(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1002"))
                .build();

        RatingEntity rating4 = RatingEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0103"))
                .score(5)
                .comment("Incredible striking!")
                .createdAt(LocalDateTime.now().minusDays(1))
                .userId(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1003"))
                .build();

        RatingEntity rating5 = RatingEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0104"))
                .score(2)
                .comment("Poor ground defense")
                .createdAt(LocalDateTime.now().minusDays(3))
                .userId(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1004"))
                .build();

        // ==== FIGHTERS ====
        FighterEntity fighter1 = FighterEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3000"))
                .nickname("Spider")
                .name("Anderson")
                .lastName("Silva")
                .weight(84.0)
                .height(1.88)
                .wins(34)
                .losses(11)
                .country(brazil)
                .coach(coach2)
                .martialArtsEntities(List.of(mma, bjj))
                .ratingsEntities(List.of(rating1))
                .build();

        FighterEntity fighter2 = FighterEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3001"))
                .nickname("Notorious")
                .name("Conor")
                .lastName("McGregor")
                .weight(70.0)
                .height(1.75)
                .wins(22)
                .losses(6)
                .country("Ireland")
                .coach(coach1)
                .martialArtsEntities(List.of(mma, muayThai))
                .ratingsEntities(List.of(rating2))
                .build();

        FighterEntity fighter3 = FighterEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3002"))
                .nickname("The Dragon")
                .name("Lyoto")
                .lastName("Machida")
                .weight(84.0)
                .height(1.85)
                .wins(26)
                .losses(12)
                .country(brazil)
                .coach(coach3)
                .martialArtsEntities(List.of(karate))
                .ratingsEntities(List.of(rating3, rating4))
                .build();

        FighterEntity fighter4 = FighterEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3003"))
                .nickname("Iron")
                .name("Mike")
                .lastName("Tyson")
                .weight(100.0)
                .height(1.78)
                .wins(50)
                .losses(6)
                .country("USA")
                .coach(coach4)
                .martialArtsEntities(List.of(boxing)) // 1 arte
                .ratingsEntities(List.of()) // sin ratings
                .build();

        FighterEntity fighter5 = FighterEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3004"))
                .nickname("The Eagle")
                .name("Khabib")
                .lastName("Nurmagomedov")
                .weight(70.0)
                .height(1.78)
                .wins(29)
                .losses(0)
                .country("Russia")
                .coach(coach5)
                .martialArtsEntities(List.of(mma, bjj, karate)) // 3 artes
                .ratingsEntities(List.of(rating1, rating5))
                .build();

        FighterEntity fighter6 = FighterEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3005"))
                .nickname("Rookie")
                .name("Alex")
                .lastName("Lopez")
                .weight(68.0)
                .height(1.72)
                .wins(0)
                .losses(0)
                .country("Mexico")
                .coach(coach4)
                .martialArtsEntities(List.of(taekwondo)) // 1 arte
                .ratingsEntities(List.of()) // sin ratings
                .build();

        FighterEntity fighter7 = FighterEntity.builder()
                .id(u("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3006"))
                .nickname("Shadow")
                .name("Maria")
                .lastName("Kuznetsova")
                .weight(61.0)
                .height(1.68)
                .wins(10)
                .losses(3)
                .country("Russia")
                .coach(coach5)
                .martialArtsEntities(List.of(k1, boxing, muayThai)) // 3 artes
                .ratingsEntities(List.of(rating4))
                .build();

        this.fighterRepository.saveAll(List.of(
                fighter1, fighter2, fighter3, fighter4, fighter5, fighter6, fighter7
        ));

        log.warn("        ------- fighters, coaches, martial arts seeded successfully");
    }

    private static UUID u(String value) {
        return UUID.fromString(value);
    }

    public void deleteAll() {
        this.fighterRepository.deleteAll();
        this.coachRepository.deleteAll();
        this.martialArtRepository.deleteAll();
    }
}
