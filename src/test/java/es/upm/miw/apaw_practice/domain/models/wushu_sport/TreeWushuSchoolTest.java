package es.upm.miw.apaw_practice.domain.models.wushu_sport;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuSchool;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.treeWushuSchool.TreeWushuSchool;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.treeWushuSchool.TreeWushuSchoolComposite;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.treeWushuSchool.TreeWushuSchoolLeaf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class TreeWushuSchoolTest {

    private TreeWushuSchool root;

    private TreeWushuSchool subWuhsuSchool;

    private TreeWushuSchool leafJingwu;
    private TreeWushuSchool leafWushuFuenlabrada;
    private TreeWushuSchool leafDaxue;

    @BeforeEach
    void setUp() {
        TreeWushuSchool sub1;
        TreeWushuSchool sub2;

        CompetitionForm[] competitionForms = {
                new CompetitionForm(9.45, Duration.ofMinutes(1).plusSeconds(30), "changquan"),
                new CompetitionForm(9.45, Duration.ofMinutes(1).plusSeconds(16), "nanquan"),
                new CompetitionForm(9.45, Duration.ofMinutes(1).plusSeconds(13), "gunshu"),
        };

        WushuGrade[] wushuGrades = {
                new WushuGrade(LocalDate.now().minusYears(5), "Jin Shi", 1),
                new WushuGrade(LocalDate.now().minusYears(4), "Yin Shi", 2),
                new WushuGrade(LocalDate.now().minusYears(3), "Jade Shi", 3),
        };

        Competitor[] competitors = {
                new Competitor("WU/A/00126", 1, LocalDate.now(), wushuGrades[0], List.of(competitionForms[0])),
                new Competitor("WU/A/00127", 2, LocalDate.now().minusYears(1), wushuGrades[1], List.of(competitionForms[1],competitionForms[2])),
                new Competitor("WU/A/00128", 3, LocalDate.now().minusYears(2), wushuGrades[2], List.of(competitionForms[0], competitionForms[1])),
        };

        WushuSchool jingwu =  new WushuSchool("Jingwu", "Madrid", false, List.of(competitors[2]));
        this.leafJingwu = new TreeWushuSchoolLeaf(jingwu);
        WushuSchool wushuFuenlabrada =  new WushuSchool("Wushu Fuenlabrada", "Fuenlabrada", true, List.of(competitors[0], competitors[1]));
        this.leafWushuFuenlabrada = new TreeWushuSchoolLeaf(wushuFuenlabrada);
        WushuSchool daxue =  new WushuSchool("Wushu Daxue", "Albacete", true, List.of(competitors[2]));
        this.leafDaxue = new TreeWushuSchoolLeaf(daxue);



        this.root= new TreeWushuSchoolComposite("Raiz");
        sub1 = new TreeWushuSchoolComposite("SUB");
        this.root.add(leafJingwu);
        sub1.add(leafWushuFuenlabrada);
        this.root.add(sub1);
        this.root.add(leafWushuFuenlabrada);

        this.subWuhsuSchool= new TreeWushuSchoolComposite("SubWushuSchool");
        this.subWuhsuSchool.add(leafDaxue);
        sub2= new TreeWushuSchoolComposite("sub2");
        this.subWuhsuSchool.add(sub2);

    }

    @Test
    void testNumberOfTreeNumbersIfLeaf() {
        assertEquals(1, this.leafJingwu.number());
        assertEquals(1, this.leafDaxue.number());
        assertEquals(1, this.leafWushuFuenlabrada.number());
    }
    @Test
    void testNumberOfTreeNumbersIfComposite() {
        assertEquals(3, this.root.number());
        assertEquals(2, this.subWuhsuSchool.number());
    }

    @Test
    void testGetAllCompetitorsLeaf(){
        List<Competitor> competitors =  this.leafJingwu.getAllCompetitors();
        assertEquals(1, competitors.size());
        competitors =  this.leafWushuFuenlabrada.getAllCompetitors();
        assertEquals(2, competitors.size());
    }

    @Test
    void testGetAllCompetitorsComposite(){
        List<Competitor> competitors =  this.root.getAllCompetitors();
        assertEquals(5, competitors.size());

    }
}
