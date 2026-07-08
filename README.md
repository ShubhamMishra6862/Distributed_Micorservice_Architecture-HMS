# Distributed Microservice Architecture - Healthcare Management System

> Production-ready healthcare platform built with microservices, demonstrating enterprise-grade patterns including service isolation, inter-service communication, and event-driven architecture.

##Demo
https://youtu.be/iN_VpAcTOWw?si=WdTEmBAXWCC1JHSM
##Deployed Swagger Documentation
https://shubhammishra6862.github.io/Distributed_Micorservice_Architecture-HMS/

## 📋 Overview

A comprehensive healthcare management system showcasing modern distributed architecture patterns. Manages patient records, appointments, billing, and authentication across isolated microservices with event-driven communication and gRPC integration.

**Key Achievements:**
- 5 independent microservices with database-per-service pattern
- Event-driven architecture using Apache Kafka
- High-performance gRPC inter-service communication
- Centralized API Gateway with request routing
- JWT-based authentication & Spring Security
- Docker containerization & Docker Compose orchestration

---

## 🏗️ Architecture

### System Diagram
<img width="1536" height="948" alt="System Diagram" src="https://github.com/user-attachments/assets/d5e71a43-6a1b-4ed7-b019-7afa4e908598" />

### Architecture Overview
```
### Service Responsibilities

| Service | Port | gRPC | Database | Role |
|---------|------|------|----------|------|
| **API Gateway** | 4004 | - | - | Central entry point, request routing, API documentation aggregation |
| **Auth Service** | 4005 | - | PostgreSQL | User authentication, JWT token generation & validation |
| **Patient Service** | 4000 | 9000 | PostgreSQL | Patient management, profile CRUD operations, event publishing |
| **Appointment Service** | 4002 | 9002 | PostgreSQL | Appointment scheduling, status management, Kafka event publishing |
| **Billing Service** | 4003 | 9003 | PostgreSQL | Billing records, invoice generation, Kafka event consumption |

---

## 🛠️ Technology Stack

### Core Framework
- **Java 21** - Latest LTS version with modern language features
- **Spring Boot 3.4** - Production-grade application framework
- **Spring Cloud 2024.0.0** - Microservices ecosystem

### Service Communication
- **Spring Cloud Gateway** - API gateway with request routing and load balancing
- **gRPC 1.80.0** - High-performance RPC framework for inter-service calls
- **Protocol Buffers 3.25.5** - Efficient data serialization

### Data & Messaging
- **PostgreSQL 15** - Relational database (per-service databases)
- **Apache Kafka 7.5.0** - Event streaming platform
- **Zookeeper 7.5.0** - Distributed coordination

### Security & API Documentation
- **Spring Security** - Authentication and authorization
- **JWT (JJWT 0.12.6)** - JSON Web Token implementation
- **SpringDoc OpenAPI 2.6-2.7** - Automated API documentation and Swagger UI

### Additional Libraries
- **Spring Data JPA** - ORM and persistence layer
- **Lombok** - Boilerplate code reduction
- **Spring Validation** - Input validation framework
- **Elasticsearch** - Search capabilities (Patient Service)

---

## 🚀 Getting Started

### Prerequisites
- Docker & Docker Compose
- Java 21 JDK
- Maven 3.8+
- Git

### Quick Start - Docker Compose

```bash
# Clone the repository
git clone <repository-url>
cd distributed-microservice-arhitecture

# Build and start all services
cd docker-package
docker-compose up --build

# Services will be available at:
# - API Gateway: http://localhost:4004
# - Auth Service: http://localhost:4005/swagger-ui.html
# - Patient Service: http://localhost:4000/swagger-ui.html
# - Appointment Service: http://localhost:4002/swagger-ui.html
# - Billing Service: http://localhost:4003/swagger-ui.html
# - Kafka: localhost:9092
# - Zookeeper: localhost:2181

# Wait 30-60 seconds for services to start
```

### Local Development

```bash
# Build all services
cd <service-directory>
./mvnw clean package

# Run individual service
./mvnw spring-boot:run

# Run as JAR
java -jar target/<service>-0.0.1-SNAPSHOT.jar
```

---

## 📡 API Endpoints

### Core Endpoints

#### Auth Service (`/auth`)
```
POST   /auth/login              - User authentication
POST   /auth/validate           - Token validation
```

#### Patient Service (`/patients`)
```
GET    /patients                - List all patients
POST   /patients                - Create new patient
GET    /patients/{id}           - Get patient details
PUT    /patients/{id}           - Update patient
DELETE /patients/{id}           - Delete patient
```

#### Appointment Service (`/appointments`)
```
GET    /appointments            - List all appointments
POST   /appointments            - Create appointment
GET    /appointments/{id}       - Get appointment details
PUT    /appointments/{id}       - Update appointment
DELETE /appointments/{id}       - Delete appointment
PUT    /appointments/{id}/status - Update appointment status
```

#### Billing Service (`/billing`)
```
POST   /billing/validate        - Validate appointment for billing
GET    /billing/history         - Billing history
GET    /billing/invoice/{id}    - Get invoice details
POST   /billing/payment         - Process payment
```

### API Documentation
Access interactive API docs via Swagger UI:
- **Auth Service**: http://localhost:4005/swagger-ui.html
- **Patient Service**: http://localhost:4000/swagger-ui.html
- **Appointment Service**: http://localhost:4002/swagger-ui.html
- **Billing Service**: http://localhost:4003/swagger-ui.html
- **API Gateway**: http://localhost:4004/swagger-ui.html

---

## 🔄 Service Communication Patterns

### REST API
Used for synchronous request-response patterns between API Gateway and services:
```
Client → API Gateway (REST) → Individual Services
```

### gRPC (RPC Calls)
High-performance inter-service communication:
- **Patient Service** (9000): Provides patient data to other services
- **Appointment Service** (9002): Service calls to validate appointments
- **Billing Service** (9003): Receives appointment data for billing

### Kafka (Event-Driven)
Asynchronous event publishing and consumption:
- **Publishers**: Patient Service, Appointment Service
- **Consumers**: Billing Service
- **Topics**: Patient events, Appointment events

**Event Flow**: 
```
Patient Created → Event Published → Stored in Kafka
Appointment Scheduled → Event Published → Billing Service Processes
```

---

## 🗄️ Database Schema

Each microservice uses its own PostgreSQL database following the database-per-service pattern:

```
auth_db/          → User credentials, tokens
patient_db/       → Patient profiles, medical history
appointment_db/   → Appointments, schedules
billing_db/       → Billing records, invoices
```

**Ports:**
- Auth DB: 5433
- Patient DB: 5434
- Appointment DB: 5437
- Billing DB: 5436

---

## 🔒 Security Features

✅ **JWT-Based Authentication**
- Token generation on login
- Token validation on requests
- Configurable expiration

✅ **Spring Security Integration**
- Password encryption
- Role-based access control (RBAC)
- Method-level security

✅ **API Gateway Protection**
- Request authentication filter
- Route protection
- CORS configuration

---

## 📊 Monitoring & Logging

### API Documentation
- **Swagger UI** integrated in each service
- **OpenAPI 3.0** specs generated automatically
- **API Gateway aggregation** for unified documentation

### Logging
- Spring Boot default logging
- Service-level logging configuration
- Request/Response interceptors

---

## 📦 Project Structure

```
distributed-microservice-architecture/
├── api-gateway/                 # Central gateway service
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── auth-service/               # Authentication service
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── patient-service/            # Patient management
│   ├── src/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/proto/              # gRPC definitions
├── appointment-service/        # Appointment management
│   ├── src/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/proto/              # gRPC definitions
├── billing-service/            # Billing & payments
│   ├── src/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/proto/              # gRPC definitions
├── docker-package/
│   └── docker-compose.yml      # Container orchestration
├── api-requests/               # HTTP client files
│   ├── auth-service/
│   ├── patient-service/
│   ├── appointment-service/
│   └── billing-service/
├── infrastructure/             # Infrastructure setup
│   └── localstack-deploy.sh   # AWS LocalStack deployment
└── project-documentation/      # API documentation
    ├── index.html
    └── specs/
```

---

## 🧪 Testing

Each service includes unit and integration tests:

```bash
# Run tests for a service
cd <service-directory>
./mvnw test

# Run with coverage
./mvnw test jacoco:report
```

---

## 🔧 Configuration

### Environment Variables
Services are configured via environment variables in `docker-compose.yml`:

```yaml
SPRING_DATASOURCE_URL: jdbc:postgresql://host:port/db
SPRING_KAFKA_BOOTSTRAP_SERVERS: kafka:9092
SPRING_PROFILES_ACTIVE: docker
GRPC_SERVER_PORT: 9000
```

### Profiles
- **docker**: Production Docker environment
- **local**: Local development configuration
- **dev**: Development with debugging enabled

---

## 📈 Key Features

✨ **Microservices Architecture**
- Independent service deployment
- Polyglot persistence (PostgreSQL per service)
- Service isolation and scalability

🔗 **Efficient Inter-Service Communication**
- gRPC for low-latency RPC calls
- Kafka for asynchronous event streaming
- REST API for client communication

🛡️ **Enterprise Security**
- JWT token-based authentication
- Spring Security integration
- Encrypted password storage

📊 **API-First Design**
- OpenAPI/Swagger documentation
- Automatic API schema generation
- Interactive API testing UI

🐳 **Cloud-Native Deployment**
- Docker containerization
- Docker Compose orchestration
- Environment-based configuration
- Multi-container networking

---

## 🚢 Deployment

### Docker Deployment
```bash
cd docker-package
docker-compose up -d
docker-compose logs -f
```

### Scaling Services
```bash
# Scale a specific service
docker-compose up -d --scale patient-service=3
```

### Stopping Services
```bash
docker-compose down -v  # -v removes volumes
```

---

## 🤝 Contributing

Guidelines for contributing to this project:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 Development Notes

### Adding a New Service
1. Create service directory
2. Set up Spring Boot project structure
3. Define data entities and repositories
4. Implement REST controllers
5. Add gRPC definitions if needed (in `src/proto/`)
6. Create Dockerfile
7. Update `docker-compose.yml`

### Service Communication Example
**Synchronous (REST/gRPC)**:
```java
// From API Gateway to Patient Service
@GetMapping("/patient/{id}")
public Patient getPatient(@PathVariable Long id) {
    return patientServiceClient.getPatient(id);
}
```

**Asynchronous (Kafka)**:
```java
// Patient Service publishes event
kafkaTemplate.send("patient-events", new PatientEvent(patient));

// Billing Service consumes event
@KafkaListener(topics = "patient-events")
public void handlePatientEvent(PatientEvent event) {
    // Process billing
}
```

---

## 📚 Documentation

Additional documentation files:
- `ARCHITECTURE.md` - Detailed architecture decisions
- `SETUP.md` - Detailed setup instructions
- `API_GUIDE.md` - API usage guide
- `DEPLOYMENT.md` - Production deployment guide

---

## 🎯 Key Competencies Demonstrated

✅ **Microservices Architecture** - Service isolation, scaling, independence  
✅ **Spring Boot/Cloud** - Production-grade applications & ecosystem  
✅ **gRPC** - High-performance inter-service communication  
✅ **Apache Kafka** - Event-driven asynchronous messaging  
✅ **PostgreSQL** - Relational database design  
✅ **Docker** - Containerization & orchestration  
✅ **JWT Security** - Token-based authentication  
✅ **API Design** - OpenAPI/Swagger documentation  
✅ **Inter-Service Communication** - Multiple patterns (REST, gRPC, Kafka)  
✅ **Database-Per-Service** - Data isolation patterns

---

## 📞 Support

For issues or questions:
1. Check existing issues in the repository
2. Create a detailed issue report
3. Include error logs and reproduction steps

---







