##  Task Description
Design the data model for Conference Room Booking System with 4 entities and their relationships.

##  Entity Details

### Entity 1: Room
| Attribute | Type | Description |
|-----------|------|-------------|
| id | Long | Primary key |
| name | String | Room name (e.g., "Conference Room A") |
| capacity | Integer | Maximum occupancy |
| floor | Integer | Floor number |
| pricePerHour | BigDecimal | Hourly rental rate |
| hasProjector | Boolean | Projector availability |

**Relationships:**
- One Room has many Bookings (1..n)
- One Room has many Amenities (n..n)

---

### Entity 2: Booking
| Attribute | Type | Description |
|-----------|------|-------------|
| id | Long | Primary key |
| startTime | LocalDateTime | Booking start time |
| endTime | LocalDateTime | Booking end time |
| attendeeCount | Integer | Number of attendees |
| purpose | String | Meeting purpose/topic |
| isConfirmed | Boolean | Confirmation status |

**Relationships:**
- Many Bookings belong to one Room (n..1)
- Many Bookings belong to one User (n..1)

---

### Entity 3: User
| Attribute | Type | Description |
|-----------|------|-------------|
| id | Long | Primary key |
| name | String | Full name |
| email | String | Email address |
| department | String | User's department |
| registeredDate | LocalDate | Registration date |

**Relationships:**
- One User has many Bookings (1..n)

---

### Entity 4: Amenity
| Attribute | Type | Description |
|-----------|------|-------------|
| id | Long | Primary key |
| name | String | Amenity name (e.g., "Whiteboard", "Microphone") |
| description | String | Detailed description |
| isAvailable | Boolean | Current availability status |

**Relationships:**
- Many Amenities belong to many Rooms (n..n)

---

## 🔗 Relationship Summary
- **Room 1 ←→ n Booking** (One-to-Many)
- **User 1 ←→ n Booking** (One-to-Many)
- **Room n ←→ n Amenity** (Many-to-Many)

---



---

##  Class Diagram
```mermaid
classDiagram
    class Room {
        +Long id
        +String name
        +Integer capacity
        +Integer floor
        +BigDecimal pricePerHour
        +Boolean hasProjector
    }
    
    class Booking {
        +Long id
        +LocalDateTime startTime
        +LocalDateTime endTime
        +Integer attendeeCount
        +String purpose
        +Boolean isConfirmed
    }
    
    class User {
        +Long id
        +String name
        +String email
        +String department
        +LocalDate registeredDate
    }
    
    class Amenity {
        +Long id
        +String name
        +String description
        +Boolean isAvailable
    }
    
    Room "1" -- "*" Booking : has
    User "1" -- "*" Booking : makes
    Room "*" -- "*" Amenity : equipped with
