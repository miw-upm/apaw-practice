package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import es.upm.miw.apaw_practice.domain.models.music_lesson.Learner;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LearnerEntity {

  @Id
  private String id;

  @Indexed(unique = true)
  private String identityDocument;

  private String name;

  private Boolean beginner;

  private List<LessonEntity> lessons;

  private BranchEntity branch;

  public LearnerEntity() {
    //empty from framework
  }

  public LearnerEntity(String identityDocument, String name, Boolean beginner, List<LessonEntity> lessons, BranchEntity branch) {
    this.id = UUID.randomUUID().toString();
    this.identityDocument = identityDocument;
    this.name = name;
    this.beginner = beginner;
    this.lessons = lessons;
    this.branch = branch;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIdentityDocument() {
    return identityDocument;
  }

  public void setIdentityDocument(String identityDocument) {
    this.identityDocument = identityDocument;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getBeginner() {
    return beginner;
  }

  public void setBeginner(Boolean beginner) {
    this.beginner = beginner;
  }

  public List<LessonEntity> getLessons() {
    return lessons;
  }

  public void setLessons(List<LessonEntity> lessons) {
    this.lessons = lessons;
  }

  public BranchEntity getBranch() {
    return branch;
  }

  public void setBranch(BranchEntity branch) {
    this.branch = branch;
  }

  public Learner toLearner() {
    Learner learner = new Learner();
    BeanUtils.copyProperties(this, learner);
    return learner;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    LearnerEntity that = (LearnerEntity) object;
    return Objects.equals(identityDocument, that.identityDocument);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(identityDocument);
  }

  @Override
  public String toString() {
    return "LearnerEntity{" +
        "id='" + id + '\'' +
        ", identityDocument='" + identityDocument + '\'' +
        ", name='" + name + '\'' +
        ", beginner=" + beginner +
        ", lessons=" + lessons +
        ", branch=" + branch +
        '}';
  }

}
