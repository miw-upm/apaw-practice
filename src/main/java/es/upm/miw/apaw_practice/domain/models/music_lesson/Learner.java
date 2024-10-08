package es.upm.miw.apaw_practice.domain.models.music_lesson;

import java.util.List;

public class Learner {

  private String identityDocument;

  private String name;

  private Boolean beginner;

  private List<Lesson> lessons;

  private Branch branch;

  public Learner() {
    //Empty for framework
  }

  public Learner(String identityDocument, String name, Boolean beginner, List<Lesson> lessons, Branch branch) {
    this.identityDocument = identityDocument;
    this.name = name;
    this.beginner = beginner;
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

  public Boolean isBeginner() {
    return beginner;
  }

  public void setBeginner(Boolean beginner) {
    this.beginner = beginner;
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
        ", isBeginner=" + beginner +
        ", lessons=" + lessons +
        ", branch=" + branch +
        '}';
  }
}
