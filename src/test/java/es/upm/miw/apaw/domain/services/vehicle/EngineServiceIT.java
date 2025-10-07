package es.upm.miw.apaw.domain.services.vehicle;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.vehicle.Engine;
import es.upm.miw.apaw.domain.persistenceports.vehicle.EnginePersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
class EngineServiceIT {

    @Autowired
    private EngineService engineService;

    @MockitoBean
    private EnginePersistence enginePersistence;

    @Test
    void testCreateEngineOk() {
        Engine engine = Engine.builder()
                .codeEngine("VMIVDS000VIS12345")
                .type("Gasolina")
                .displacement(1250.00)
                .build();

        BDDMockito.given(this.enginePersistence.existCodeEngine("VMIVDS000VIS12345")).willReturn(false);
        BDDMockito.given(this.enginePersistence.create(any(Engine.class))).willReturn(engine);

        Engine created = this.engineService.create(engine);

        assertThat(created).isNotNull();
        assertThat(created.getCodeEngine()).isEqualTo("VMIVDS000VIS12345");
    }

    @Test
    void testCreateEngineConflict() {
        Engine engine = Engine.builder()
                .codeEngine("VMIVDS000VIS12345")
                .type("Gasolina")
                .displacement(1250.00)
                .build();

        BDDMockito.given(this.enginePersistence.existCodeEngine("VMIVDS000VIS12345")).willReturn(true);

        assertThatThrownBy(() -> this.engineService.create(engine))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Code engine exist: VMIVDS000VIS12345");
    }

    @Test
    void testUpdateOk() {
        Engine engine = Engine.builder()
                .codeEngine("VMIVDS000VIS00001")
                .type("Diesel")
                .displacement(1800.00)
                .build();

        BDDMockito.given(this.enginePersistence.update(any(Engine.class))).willReturn(engine);

        Engine updated = this.engineService.update("VMIVDS000VIS00001", engine);

        assertThat(updated.getCodeEngine()).isEqualTo("VMIVDS000VIS00001");
        assertThat(updated.getType()).isEqualTo("Diesel");
    }

    @Test
    void testUpdateConflictUriBodyMismatch() {
        Engine engine = Engine.builder()
                .codeEngine("CODE_BODY")
                .type("Diesel")
                .displacement(1800.00)
                .build();

        assertThatThrownBy(() -> this.engineService.update("CODE_URI", engine))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("The engine code of the URI (CODE_URI) is not the same as that of the body (CODE_BODY).");
    }
}
