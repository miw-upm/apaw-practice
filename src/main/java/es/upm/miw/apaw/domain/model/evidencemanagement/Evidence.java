package es.upm.miw.apaw.domain.model.evidencemanagement;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Evidence {

    private UUID id;
    private String title;
    private String description;
    private EvidenceType evidenceType;
    private EvidenceStatus status;
    private LocalDateTime collectionDate;
    private String source;
    private boolean confidential;
    private final List<CustodyRecord> custodyRecords = new ArrayList<>();

    public Evidence(UUID id, String title, String description, EvidenceType evidenceType,
                    EvidenceStatus status, LocalDateTime collectionDate, String source,
                    boolean confidential) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.evidenceType = evidenceType;
        this.status = status;
        this.collectionDate = collectionDate;
        this.source = source;
        this.confidential = confidential;
    }

    public void addCustodyRecord(CustodyRecord custodyRecord) {
        this.custodyRecords.add(custodyRecord);
    }
}

