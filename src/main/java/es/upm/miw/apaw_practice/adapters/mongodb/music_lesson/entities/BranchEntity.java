package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities;

import java.util.Objects;
import java.util.UUID;

import es.upm.miw.apaw_practice.domain.models.music_lesson.Branch;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BranchEntity {

  @Id
  private String id;

  @Indexed(unique = true)
  private String code;

  private String address;

  private String phoneNumber;

  public BranchEntity() {
    //empty from framework
  }

  public BranchEntity(String code, String address, String phoneNumber) {
    this.id = UUID.randomUUID().toString();
    this.code = code;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public Branch toBranch() {
    Branch branch = new Branch();
    BeanUtils.copyProperties(this, branch);
    return branch;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.code);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    BranchEntity that = (BranchEntity) object;
    return Objects.equals(code, that.code);
  }

  @Override
  public String toString() {
    return "BranchEntity{" +
        "id='" + id + '\'' +
        ", code='" + code + '\'' +
        ", address='" + address + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        '}';
  }
}
