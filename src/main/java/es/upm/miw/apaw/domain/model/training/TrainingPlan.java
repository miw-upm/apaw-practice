package es.upm.miw.apaw.domain.model.training;

import es.upm.miw.apaw.domain.model.UserSnapshot;

import java.time.LocalDate;
import java.util.List;

public class TrainingPlan {
    private String id;
    private String planCode;
    private LocalDate approvalDate;
    private LocalDate endDate;
    private Double evaluationScore;
    
    private Course course;
    private List<UserSnapshot> userSnapshots;

    public TrainingPlan(String id, String planCode, LocalDate approvalDate, LocalDate endDate, Double evaluationScore, Course course, List<UserSnapshot> userSnapshots) {
        this.id = id;
        this.planCode = planCode;
        this.approvalDate = approvalDate;
        this.endDate = endDate;
        this.evaluationScore = evaluationScore;
        this.course = course;
        this.userSnapshots = userSnapshots;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(Double evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<UserSnapshot> getUserSnapshots() {
        return userSnapshots;
    }

    public void setUserSnapshots(List<UserSnapshot> userSnapshots) {
        this.userSnapshots = userSnapshots;
    }
}
