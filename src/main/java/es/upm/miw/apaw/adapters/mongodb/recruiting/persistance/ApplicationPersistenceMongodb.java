package es.upm.miw.apaw.adapters.mongodb.recruiting.persistance;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.ApplicationRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.MeetingEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Application;
import es.upm.miw.apaw.domain.persistenceports.recruiting.ApplicationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("applicationPersistence")
public class ApplicationPersistenceMongodb implements ApplicationPersistence {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationPersistenceMongodb(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application readById(UUID id) {
        return this.applicationRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Application id:" + id))
                .toApplication();
    }

    @Override
    public Application update(Application application) {
        ApplicationEntity applicationEntity = this.applicationRepository
                .findById(application.getId())
                .orElseThrow(() -> new NotFoundException("Application id:" + application.getId()));

        if (application.getMeetingList() != null) {
            List<MeetingEntity> meetingEntities = application.getMeetingList().stream()
                    .map(meeting -> MeetingEntity.builder()
                            .id(UUID.randomUUID())
                            .date(meeting.getDate())
                            .url(meeting.getUrl())
                            .attendees(null)
                            .build())
                    .toList();

            applicationEntity.setMeetingList(meetingEntities);
        }

        ApplicationEntity saved = this.applicationRepository.save(applicationEntity);
        return saved.toApplication();
    }
}