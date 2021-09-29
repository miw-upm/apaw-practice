package es.upm.miw.apaw_practice.domain.models.tv_series;

public class Producer {
    private String businessName;
    private String email;
    private Long phone;

    public Producer() {
        // empty for framework
    }

    public Producer(String businessName, String email, Long phone) {
        this.businessName = businessName;
        this.email = email;
        this.phone = phone;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "businessName='" + this.businessName + '\'' +
                ", email='" + this.email + '\'' +
                ", phone=" + this.phone +
                '}';
    }
}
