# Guía de estilo y arquitectura

Documento normativo para contribuir en `apaw-practice`, ejercicio docente de Arquitectura y Patrones para Aplicaciones Web.
Se aplica a todo el repositorio. La arquitectura es hexagonal: el dominio define sus necesidades mediante puertos
y los adaptadores implementan el acceso a tecnologías externas.

## Criterios de corrección

Cada criterio puede descontar puntos. La letra marca la penalización orientativa:

- a = -0.2
- b = -0.4
- c = -0.6
- d = -0.8
- e = -1
- f = -1.5
- g = -2
- h = -2.5
- i = -3
- j = -5
- K = -10

Coste de incumplir: cuando una sección indique un coste común, solo se etiqueta la regla que se sale de ese coste.
Se conserva la escala del documento de referencia y se adaptan sus secciones a las responsabilidades hexagonales.
Las descripciones del estado actual y los ejemplos de ubicaciones aportan contexto; no son criterios penalizables.

## Niveles de regla

- `DEBE`: obligatorio.
- `NO DEBE`: prohibido.
- `DEBERÍA`: recomendado, salvo razón técnica explícita.
- `PUEDE`: opcional.

## Alcance y estado del proyecto

- Esta guía conserva el formato normativo y las penalizaciones orientativas de `apaw-user`, adaptando
  las responsabilidades y dependencias a la arquitectura hexagonal de este proyecto.
- Las reglas de ampliación describen cómo implementar funcionalidades nuevas; no implican que ya existan.
- Actualmente están implementados el arranque, configuración, endpoints de sistema, manejo de errores y
  `UserSnapshot`. Los servicios, puertos y adaptadores de negocio están representados por archivos `.txt` vacíos.
- Esos archivos reservan nombres y ubicaciones: NO DEBEN tratarse como clases compiladas ni como contratos definidos.
  Al implementar la pieza correspondiente, DEBE crearse el `.java`; PUEDE retirarse su marcador vacío.
- `README.md` conserva referencias a arquitectura por capas y MongoDB. `docs/hexagonal.png` ilustra la inversión
  de dependencias, pero también incluye nombres y tecnologías anteriores. Para rutas y tecnología actual,
  DEBE contrastarse la documentación con `src`, `pom.xml` y la configuración.

## Estructura de carpetas

Paquete base: `src/main/java/es/upm/miw/apaw`.

```text
es/upm/miw/apaw/
  Application.java
  domain/
    models/                          # Modelo propio; actualmente UserSnapshot
    services/
      legalprocedure/                # Marcadores LegalProcedureService y LegalTaskService
    ports/out/
      legalprocedure/                # Marcadores LegalProcedureGateway y LegalTaskGateway
      user/                          # Marcador UserFinder
    exceptions/                      # Errores que pueden cruzar la frontera del dominio
  adapters/
    in/
      system/                        # SystemResource, ApplicationInfoDto, VersionBadgeGenerator
      exceptionshandler/             # ApiExceptionHandler y ErrorMessage
    out/
      legalprocedure/postgres/
        legalprocedure/              # Reserva para persistencia de procedimientos
        legaltask/                   # Reserva para persistencia de tareas
      user/feign/                    # Marcadores ApawUserClient y UserFinderAdapter
  config/                            # Seguridad, logging y configuración de Eureka/HTTP
```

Otras ubicaciones:

- `src/main/resources`: configuración común y perfiles `dev` y `prod`.
- `src/test/java/es/upm/miw/apaw`: tests; los funcionales existentes están en `functionaltests/system`.
- `src/test/resources`: perfil `test` y configuración de logging de pruebas.
- `docs`: imágenes de apoyo; `.github/workflows`: integración y despliegue.
- `target`: resultados generados por Maven. NO DEBE editarse como fuente.

Las nuevas áreas DEBEN mantener la agrupación por funcionalidad dentro de cada frontera arquitectónica.
NO DEBEN crearse árboles alternativos de primer nivel `resources`, `services` o `infrastructure` como en `apaw-user`.

## Dependencias y flujo de ejecución

Dependencias de código permitidas:

```text
adapters.in  --> domain.services --> domain.ports.out
                    |                    |
                    +--> domain.models <--+
adapters.out --> domain.ports.out + domain.models + domain.exceptions
config       --> componentes necesarios para configurar y conectar la aplicación
```

En ejecución, un caso de uso llama a un puerto y Spring conecta su implementación de salida.
Por ejemplo, al desarrollar procedimientos: `Resource → LegalProcedureService → LegalProcedureGateway`,
resuelto mediante un adaptador PostgreSQL que usa un repositorio JPA.

- `domain` NO DEBE importar `adapters` ni `config`.
- Los servicios NO DEBEN conocer repositorios Spring Data, entidades JPA, clientes Feign, SQL ni respuestas HTTP.
- Los adaptadores de entrada de negocio DEBEN delegar en servicios; NO DEBEN acceder directamente a adaptadores
  de salida, clientes HTTP ni repositorios de persistencia.
- Los adaptadores de salida DEBEN implementar puertos del dominio; NO DEBEN invocar resources ni coordinar casos de uso.
- Los modelos, puertos y excepciones NO DEBEN depender de servicios.
- Los puertos DEBEN ser interfaces Java expresadas en términos del dominio, sin tipos de JPA, Feign o Spring MVC.
- NO DEBE añadirse una interfaz de entrada por servicio de forma automática: actualmente no existe `domain.ports.in`.
  Los servicios públicos son la entrada a los casos de uso; una interfaz adicional requiere una necesidad concreta.
- PUEDE usarse `@Service` e inyección por constructor en servicios como decisión pragmática de integración con Spring.
  Esto no permite introducir dependencias de persistencia o transporte en sus contratos.
- PUEDE mantenerse Lombok y Jakarta Validation en modelos, como hace `UserSnapshot`.

## Convenciones de nombres (b)

- DEBE usar nombres descriptivos en inglés: PascalCase para tipos, camelCase para métodos y variables,
  mayúsculas con guion bajo para constantes y minúsculas para paquetes.
- DEBE usar sufijos según responsabilidad: `Resource`, `Dto`, `Service`, `Gateway`, `Finder`, `Writer`, `Adapter`,
  `Entity` y `Repository`. `ApawUserClient` queda reservado al cliente HTTP, no al puerto.
- DEBE usar PascalCase para enums y mayúsculas para sus valores.
- DEBE usar prefijos `is`, `has` o `can` para predicados y mantener una convención uniforme entre los relacionados.
- DEBERÍA evitar abreviaturas, salvo las habituales (`id`, `url`, `dto`, `dao`).
- NO DEBE usar prefijos de tipo ni notación húngara.

## Estilo de código (a)

- DEBE usar `this.` para miembros de instancia en código nuevo y respetar el formato de las clases vecinas.
- DEBE usar inyección por constructor; PUEDE simplificarse con `@RequiredArgsConstructor` y atributos `final`.
  `@Value` PUEDE utilizarse para propiedades de configuración.
- DEBE usar logging con `@Log4j2` o `LogManager.getLogger()`, sin `System.out.print*`.
- (b) Los mensajes de negocio DEBEN incluir el dato causante cuando ayude a identificar el error,
  sin exponer credenciales o tokens.
- DEBERÍA aclarar el código con nombres y métodos expresivos antes de añadir comentarios explicativos.
- NO DEBE realizar refactorizaciones generales o cambios de estilo ajenos a la tarea.

## Límites de tamaño (b-c)

Son umbrales de revisión. El criterio principal es responsabilidad única y legibilidad.

- DEBERÍA mantener un máximo de 3 parámetros por método; agrupar filtros relacionados en un `FindCriteria`.
- DEBERÍA mantener un máximo de 20 líneas por método.
- DEBERÍA mantener complejidad ciclomática máxima de 8 y un máximo de 2 niveles de anidamiento.
- DEBERÍA mantener un máximo de 20 métodos públicos y 6 dependencias inyectadas por clase.
- DEBERÍA mantener un máximo de 250 líneas por clase y 120 caracteres por línea.
- DEBERÍA mantener un máximo de 8 atributos, salvo modelos, entidades y DTOs con más campos reales del recurso.
- Los tests y seeders PUEDEN superar los umbrales cuando dividirlos perjudique su claridad.
- NO DEBE crear clases, DTOs o abstracciones únicamente para cumplir una cifra.

## Modelos de dominio (e-f)

- DEBEN ubicarse en `domain.models`, agrupados por funcionalidad cuando crezca el modelo.
- DEBEN representar conceptos y reglas propias, sin `@Entity`, relaciones JPA ni anotaciones de transporte HTTP.
- DEBEN encapsular comportamiento derivado de su estado; los servicios coordinan las reglas que requieren otros datos.
- `UserSnapshot` representa una vista local de información externa. NO DEBE convertirse en entidad JPA de usuarios
  ni depender del DTO remoto o del cliente Feign.
- Los modelos de dominio y las entidades de persistencia DEBEN ser tipos separados cuando se implemente persistencia.
  La separación es una frontera arquitectónica, aunque algunos campos coincidan.
- (f) DEBE utilizar identidad técnica `UUID` para nuevas entidades propias, coherente con `UserSnapshot.id`.
  Las referencias a sistemas externos DEBEN conservar la identidad definida por su contrato.
- Las claves naturales como móvil NO DEBEN sustituir la identidad técnica por conveniencia.
- Los valores por defecto de creación DEBEN aplicarse desde el caso de uso, apoyándose en el modelo si procede;
  NO DEBEN activarse durante la carga desde persistencia o desde un DTO.
- Si se necesitan filtros, datos de creación o informes, PUEDEN añadirse tipos en `domain.models`
  (por ejemplo, subpaquetes `criteria`, `creations` o `reports`). Son propuestas de ampliación, no carpetas existentes.
- (c-d) Un `FindCriteria` DEBE expresar filtros y su ausencia; NO DEBE contener SQL ni detalles de presentación.
  NO DEBE crearse un tipo por cada combinación de filtros.

## Servicios y puertos de salida (e-f)

- Los casos de uso DEBEN ubicarse en `domain.services.<funcionalidad>` y usar sufijo `Service`.
- DEBEN trabajar con modelos y puertos del dominio, sin estado mutable propio de una petición.
- DEBEN decidir reglas de creación, actualización, existencia, unicidad y coordinación entre capacidades.
- DEBERÍAN usar nombres consistentes como `create`, `read`, `update`, `delete` y `find`.
- El contrato DEBE definir el comportamiento ante ausencia; lecturas y actualizaciones de recursos requeridos
  DEBEN comunicar `NotFoundException`. El borrado DEBERÍA ser idempotente salvo requisito contrario.
- Los puertos DEBEN ubicarse en `domain.ports.out.<funcionalidad>` y declarar solo las operaciones necesarias.
- DEBE respetarse la intención de los nombres reservados: `LegalProcedureGateway` y `LegalTaskGateway`
  para acceso a datos; `UserFinder` para consulta de usuarios externos.
- Cada operación del puerto DEBE definir el significado de ausencia y error y usar tipos de dominio o Java.
  PUEDE devolver `Optional` cuando la ausencia sea un resultado válido.
- NO DEBE extender `JpaRepository` desde un puerto ni devolver entidades de persistencia o DTOs remotos.
- Si un caso de uso exige varias escrituras locales atómicas, DEBE delimitarse su transacción de manera explícita.
  PUEDE usarse `@Transactional` en el servicio sin introducir JPA en su lógica.
- NO DEBE asumirse que una transacción local hace atómica una llamada HTTP a otro servicio.

### Semántica de los sufijos de los puertos (e-f)

Los sufijos expresan qué capacidad necesita el dominio y el alcance de su contrato. No identifican la tecnología
del adaptador. Esta convención distingue el acceso a datos propios de las capacidades concretas de servicios externos.

| Sufijo | Responsabilidad | Ejemplo del proyecto o del esquema |
| --- | --- | --- |
| `*Gateway` | Acceso a la persistencia de un concepto del dominio, con las operaciones de lectura y escritura necesarias. | `LegalProcedureGateway`, `LegalTaskGateway` |
| `*Finder` | Consulta de información, sin operaciones de modificación en su contrato. | `UserFinder`, que devuelve información como `UserSnapshot` |
| `*Writer` | Escritura, envío o ejecución de una acción de salida, sin incorporar consultas independientes. | `EmailWriter`, representado en `docs/hexagonal.png` |

`EmailWriter` es un ejemplo del esquema arquitectónico; actualmente no existe su archivo en `domain.ports.out`.

- DEBE elegir el sufijo por la responsabilidad requerida por el caso de uso, no por usar PostgreSQL, Feign o HTTP.
- DEBE reservar `Gateway` para el acceso al concepto persistido; PUEDE reunir lectura y escritura sin obligar
  a implementar un CRUD completo si el dominio no lo necesita.
- NO DEBE añadir operaciones de creación, modificación o borrado a un `Finder`.
- NO DEBE añadir búsquedas independientes a un `Writer`; PUEDE devolver el resultado o la confirmación de su acción.
- DEBE expresar capacidades externas concretas mediante nombres como `UserFinder` o `EmailWriter`, evitando
  contratos amplios que reproduzcan toda la API remota.
- NO DEBE confundir el puerto `UserFinder` con el cliente `ApawUserClient` ni con su implementación `UserFinderAdapter`.
- Un adaptador PUEDE implementar varios puertos si tiene sentido; el servicio DEBE depender solo de las capacidades
  que necesita. Separar `Finder` y `Writer` no obliga a introducir CQRS ni bases de datos diferentes.

## Adaptadores de entrada y DTOs (e-f)

- Los nuevos endpoints DEBEN ubicarse en `adapters.in.<funcionalidad>`, con `@RestController` y sufijo `Resource`.
- DEBEN declarar rutas mediante constantes y delegar las decisiones de negocio en el servicio correspondiente.
- Los DTOs HTTP DEBEN permanecer en el adaptador de entrada, junto al recurso o en su subpaquete `dtos`.
- DEBEN validar la entrada mediante Jakarta Validation y `@Valid` cuando corresponda.
- La conversión DTO ↔ dominio DEBE realizarse en el adaptador de entrada, mediante constructor, `toDomain()`
  o un mapper local cuando la complejidad lo justifique.
- NO DEBEN exponerse entidades JPA ni introducir DTOs HTTP en servicios o puertos.
- DEBERÍA evitarse multiplicar DTOs por operación; cada variante debe responder a un contrato distinto.
  PUEDEN usarse records para datos simples, como `ApplicationInfoDto`.
- Los DTOs NO DEBEN consultar datos ni decidir valores por defecto de negocio.
- `SystemResource` es una entrada técnica: PUEDE consultar metadatos y generar el badge sin un servicio de negocio.
  DEBEN preservarse `/system` y `/system/version-badge`, incluido su contenido SVG, salvo cambio solicitado.

## Adaptadores PostgreSQL y JPA (e-f)

- DEBEN ubicarse en `adapters.out.<funcionalidad>.postgres`, respetando las reservas de `legalprocedure` y `legaltask`.
- DEBEN separar la implementación del puerto (`*Adapter`), el modelo persistido (`*Entity`) y el repositorio
  Spring Data (`*Repository`). Estas convenciones se aplican a las clases que se incorporen.
- El adaptador DEBE implementar el puerto e inyectar el repositorio; el servicio solo inyecta el puerto.
- Las entidades DEBEN usar `@Entity` y `jakarta.persistence.Id`; los repositorios DEBEN extender
  `JpaRepository<Entidad, TipoId>` dentro del adaptador.
- La conversión entidad ↔ dominio DEBE completarse en el adaptador de salida, sin consultas desde el modelo de dominio.
- DEBE conservarse la identidad en las conversiones y actualizaciones, sin generar otra identidad al leer.
- Las restricciones de unicidad y obligatoriedad DEBEN respaldarse en la base de datos cuando sean necesarias.
  Una comprobación previa de existencia no sustituye la restricción de integridad.
- Los enums persistidos DEBERÍAN usar `EnumType.STRING`; las relaciones JPA DEBEN permanecer entre entidades JPA.
- DEBERÍA usar consultas derivadas para búsquedas simples. SQL, JPQL, consultas custom y sus optimizaciones
  DEBEN permanecer en este adaptador.
- DEBE realizarse la carga necesaria para convertir el resultado dentro del ámbito de persistencia apropiado:
  la configuración mantiene `spring.jpa.open-in-view: false`.
- NO DEBE dependerse de una sesión JPA abierta durante la serialización HTTP.

## Adaptadores HTTP salientes (c-d)

- La integración prevista con usuarios DEBE ubicarse en `adapters.out.user.feign`.
- `ApawUserClient` DEBE encapsular el contrato HTTP de Feign; `UserFinderAdapter` DEBE implementar `UserFinder`
  y convertir los datos recibidos a `UserSnapshot`.
- Los DTOs remotos y detalles de rutas, cabeceras, códigos de estado y serialización DEBEN permanecer en el adaptador.
- El servicio DEBE decidir cuándo consultar al usuario; el adaptador decide cómo realizar la comunicación.
- URLs, nombres de servicio y timeouts DEBEN configurarse fuera del dominio.
- Los fallos de Feign o transporte NO DEBEN propagarse como contrato técnico del puerto.
  El adaptador DEBE traducirlos a una excepción apropiada de `domain.exceptions`, conservando la causa.
- DEBE distinguirse un dato inexistente de una indisponibilidad remota; NO DEBE convertir cualquier fallo en
  `NotFoundException` ni devolver un snapshot vacío como sustituto de un error.

## Excepciones y contrato de errores (e-f)

- Las excepciones compartidas DEBEN ubicarse en `domain.exceptions`; la traducción a HTTP pertenece a
  `adapters.in.exceptionshandler.ApiExceptionHandler`.
- DEBE reutilizarse `ErrorMessage` cuando la respuesta tenga cuerpo de error, con `error`, `message` y `cause`.
- La jerarquía `ApiException` conserva detalle y causa. NO DEBE incorporar tipos HTTP o anotaciones Spring MVC.
- El manejador actual traduce `NotFoundException` a 404, `BadRequestException` a 400, `ConflictException` y
  `ClientBusinessException` a 409, `ForbiddenException` a 403 y `BadGatewayException` a 502.
- Actualmente `UnauthorizedException`, `InfrastructureException` e `InternalServerException` no tienen
  manejadores propios. NO DEBE inferirse un estado HTTP por su nombre: al utilizarlas, DEBE revisarse el mapeo.
  `AccessDeniedException` de Spring Security tiene actualmente tratamiento 401 sin cuerpo.
- DEBE conservarse el estado y las cabeceras de `ResponseStatusException`.
- El manejador general de `Exception` con 500 DEBE reservarse para errores imprevistos y registrarlos con nivel `error`.
- Los errores conocidos DEBEN tener tratamiento explícito acorde al contrato, con pruebas del mapeo afectado.
- NO DEBE atribuirse toda violación de integridad a un duplicado concreto sin comprobar su causa.
- Los detalles devueltos o registrados NO DEBEN incluir secretos ni información sensible innecesaria.

## Configuración e inicializadores (e-f)

- `config` DEBE contener configuración técnica y conexión de componentes, sin reglas de negocio.
- `Application` habilita Feign y excluye `ErrorMvcAutoConfiguration`; DEBE tenerse en cuenta al cambiar errores HTTP.
- DEBE preservarse la configuración de seguridad existente salvo cambios solicitados; NO DEBEN añadirse
  permisos o mecanismos de autenticación como parte incidental de una funcionalidad.
- Actualmente no existe seeder. Si una tarea necesita uno, DEBE limitarse a `dev` y `test`, usar un inicializador
  explícito como `ApplicationRunner` y mantener referencias estables para pruebas.

## Docker y perfiles (c-d)

- DEBE mantener la configuración común en `application.yml` y las diferencias por entorno en sus perfiles.
  `dev` es el perfil predeterminado y usa PostgreSQL local; `prod` usa credenciales de entorno y nombres de
  contenedores; `test` usa H2 y deshabilita Eureka.
- Docker usa el puerto 8082 y `/actuator/health`. Compose reutiliza PostgreSQL y Eureka en la red externa `apawnet`;
  NO DEBE asumirse que crea esos servicios ni que activa automáticamente `prod`.
- NO DEBE usarse `localhost` para dirigirse a otro contenedor; las direcciones DEBEN ser accesibles desde el cliente.
- Cambiar código NO autoriza por sí mismo a publicar o desplegar. Los workflows de CD publican imágenes;
  staging tiene despliegue activo y el paso de despliegue de main está comentado.

## Tests (e-f)

- DEBE seguirse JUnit Jupiter y AssertJ, con tests independientes y nombres `testXxx` en nuevas pruebas de comportamiento.
- `*Test`: unitarios o carga de contexto, como `ApplicationTest`; `*IT`: integración; `*FT`: funcionales HTTP.
- Los nuevos tests unitarios de servicios DEBERÍAN sustituir los puertos con dobles, sin necesitar HTTP ni base de datos.
- Los tests de adaptadores de persistencia DEBEN comprobar conversiones, consultas e integridad relevantes.
  H2 no garantiza todas las particularidades de PostgreSQL; si una consulta depende de ellas, DEBE verificarse
  en un entorno apropiado y comunicarse cualquier limitación pendiente.
- Los tests de integración con contexto DEBEN usar `@SpringBootTest` y `@ActiveProfiles("test")` cuando corresponda.
- Los funcionales HTTP DEBEN seguir `SystemResourceFT`: `RANDOM_PORT`, `@LocalServerPort`, perfil `test` y
  `RestTestClient.bindToServer()`. DEBEN situarse en `functionaltests/<funcionalidad>`.
- DEBE probarse negocio en servicios, comportamiento técnico en adaptadores y contrato HTTP en funcionales,
  incluidos los errores relevantes. DEBERÍA usarse `assertThatThrownBy` para excepciones de servicios.
- Los tests ordinarios NO DEBEN requerir un Eureka activo ni el servicio real de usuarios; DEBEN aislar esa integración.
- Cada test DEBE preparar sus datos adicionales y NO DEBE depender del orden de ejecución.
- Si se incorpora seeder, ampliarlo NO DEBE romper pruebas anteriores: comprobar pertenencia o exclusión en
  búsquedas generales, sin exigir el tamaño total ni un orden no definido por el contrato.
- Los tests de borrado DEBEN crear sus propios datos; los cambios a referencias compartidas DEBEN aislarse o restaurarse.

## Tecnología y build

- DEBE mantenerse Java 21 y Maven. `pom.xml` es la referencia de versiones y dependencias:
  Spring Boot, Spring MVC, JPA, PostgreSQL, H2 para tests, Validation, Lombok, OpenAPI, Eureka y OpenFeign.
- DEBE consultar `pom.xml` antes de modificar versiones y dependencias.
- WebFlux está incluido para tests; NO DEBE migrarse el servidor MVC a reactivo sin una tarea específica.

Comandos desde la raíz, con Java 21 y Maven instalados (no hay Maven Wrapper en el repositorio):

```sh
mvn test
mvn -B verify
```

- `mvn test` ejecuta las pruebas de Surefire; NO sustituye la ejecución de integración y funcionales.
- `mvn -B verify` ejecuta también `*IT` y `*FT` mediante Failsafe y es el comando de CI en `.github/workflows/ci.yml`.
- Para cambios de código, DEBEN ejecutarse las comprobaciones apropiadas y DEBERÍA completarse `mvn -B verify`.
- Los cambios exclusivamente documentales PUEDEN verificarse revisando contenido y diff, sin ejecutar Maven.
- DEBE distinguirse entre revisión estática, tests ejecutados y comprobaciones pendientes o bloqueadas.

## Antipatrones prohibidos

- Servicios que importan adaptadores, repositorios JPA, entidades persistidas o clientes Feign.
- Puertos definidos por la tecnología externa en lugar de las necesidades del dominio.
- Resources que acceden a la base de datos o implementan reglas de negocio.
- Entidades JPA utilizadas como modelos de dominio o respuestas HTTP.
- Modelos que consultan repositorios o conocen DTOs y servicios.
- Reglas de negocio en DTOs, mappers, clientes HTTP o configuración.
- Excepciones técnicas de integraciones filtradas a los contratos del dominio.
- Activación masiva de marcadores `.txt`, nuevas abstracciones o cambios de tecnología sin relación con la tarea.
- Tests dependientes del orden, de datos globales mutables o del tamaño completo de un futuro seeder.

