package es.upm.miw.apaw_practice.domain.models.music_lesson;

public class Branch {

  private String code;

  private String address;

  private String phoneNumber;

  public Branch() {
    //Empty for framework
  }

  public Branch(String code, String address, String phoneNumber) {
    this.code = code;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String toString() {
    return "Branch{" +
        "code='" + code + '\'' +
        ", address='" + address + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        '}';
  }
}
