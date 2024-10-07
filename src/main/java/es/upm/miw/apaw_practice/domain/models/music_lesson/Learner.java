package es.upm.miw.apaw_practice.domain.models.music_lesson;

import java.util.List;

public class Learner {

  private String identityDocument;

  private String name;

  private Boolean isBeginner;

  private List<Lesson> lessons;

  private Branch branch;

  public Learner() {
    //Empty for framework
  }

  public Learner(String identityDocument, String name, Boolean isBeginner, List<Lesson> lessons, Branch branch) {
    this.identityDocument = identityDocument;
    this.name = name;
    this.isBeginner = isBeginner;
    this.lessons = lessons;
    this.branch = branch;
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
    return isBeginner;
  }

  public void setBeginner(Boolean beginner) {
    isBeginner = beginner;
  }

  public List<Lesson> getLessons() {
    return lessons;
  }

  public void setLessons(List<Lesson> lessons) {
    this.lessons = lessons;
  }

  public Branch getBranch() {
    return branch;
  }

  public void setBranch(Branch branch) {
    this.branch = branch;
  }

  @Override
  public String toString() {
    return "Learner{" +
        "identityDocument='" + identityDocument + '\'' +
        ", name='" + name + '\'' +
        ", isBeginner=" + isBeginner +
        ", lessons=" + lessons +
        ", branch=" + branch +
        '}';
  }
}
