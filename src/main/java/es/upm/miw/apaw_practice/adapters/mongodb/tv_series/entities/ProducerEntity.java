package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities;

import es.upm.miw.apaw_practice.domain.models.tv_series.Producer;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document
public class ProducerEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String businessName;
    private String email;
    private Long phone;

    public ProducerEntity() {
        // empty for framework
    }

    public ProducerEntity(Producer producer) {
        BeanUtils.copyProperties(producer, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void fromProducer(Producer producer) {
        BeanUtils.copyProperties(producer,this);
    }

    public Producer toProducer() {
        Producer producer = new Producer();
        BeanUtils.copyProperties(this, producer);
        return producer;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (businessName.equals(((ProducerEntity) obj).businessName));
    }

    @Override
    public int hashCode() {
        return businessName.hashCode();
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id='" + this.id + '\'' +
                ", businessName='" + this.businessName + '\'' +
                ", email='" + this.email + '\'' +
                ", phone=" + this.phone +
                '}';
    }
}
