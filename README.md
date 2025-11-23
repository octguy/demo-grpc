# Demo gRPC Application

This is a demo application showcasing gRPC communication between two microservices built with Spring Boot.

## Architecture

The application consists of two services:

- **Service A** (HTTP REST API) - Runs on port 8081
- **Service B** (gRPC Server) - Runs on port 8082 (HTTP) and 9090 (gRPC)

Service A exposes a REST endpoint and communicates with Service B via gRPC to process requests.

## Prerequisites

- Docker and Docker Compose installed on your system
- Java 11+ (if running locally without Docker)
- Maven (if running locally without Docker)

## Quick Start

### 1. Start the Application

Run the following command from the project root directory:

```bash
docker-compose up -d
```

This will:

- Build both services
- Start Service B (gRPC server) on ports 8082 and 9090
- Start Service A (REST API) on port 8081
- Create a network for inter-service communication

### 2. Verify Services are Running

Check that both services are running:

```bash
docker-compose ps
```

You should see both `service-a` and `service-b` containers in "Up" status.

### 3. Test the Application

Send a message to the REST endpoint:

```bash
curl -X GET http://localhost:8081/hello \
  -H "Content-Type: application/json" \
  -d "Hello from the client!"
```

**Expected Response:** You should receive a response processed by Service B via gRPC.

## API Endpoints

### Service A (REST API)

- **URL:** `http://localhost:8081/hello`
- **Method:** GET
- **Request Body:** Plain text string
- **Response:** Processed message from Service B

### Service B (gRPC)

- **gRPC Port:** 9090
- **HTTP Port:** 8082
- **Service:** HelloService
- **Method:** SendMessage

## Example Usage

### Basic Request

```bash
curl -X GET http://localhost:8081/hello \
  -H "Content-Type: application/json" \
  -d "Test message"
```

## Project Structure

```
demo-grpc/
├── docker-compose.yml          # Docker Compose configuration
├── start.sh                    # Startup script
├── service-a/                  # REST API service
│   ├── src/main/java/          # Java source code
│   ├── src/main/proto/         # Protocol Buffer definitions
│   └── Dockerfile              # Docker configuration
└── service-b/                  # gRPC server service
    ├── src/main/java/          # Java source code
    ├── src/main/proto/         # Protocol Buffer definitions
    └── Dockerfile              # Docker configuration
```

## Protocol Buffer Definition

Both services use the following gRPC service definition (`hello.proto`):

```protobuf
service HelloService {
  rpc SendMessage(HelloRequest) returns (HelloResponse);
}

message HelloRequest {
  string message = 1;
}

message HelloResponse {
  string message = 1;
}
```

## Development Commands

### Stop the Application

```bash
docker-compose down
```

### View Logs

```bash
# View all service logs
docker-compose logs

# View specific service logs
docker-compose logs service-a
docker-compose logs service-b

# Follow logs in real-time
docker-compose logs -f
```

### Rebuild Services

```bash
# Rebuild and restart
docker-compose up -d --build

# Rebuild specific service
docker-compose build service-a
```

### Build Locally (without Docker)

```bash
# Build Service A
cd service-a
./mvnw clean package

# Build Service B
cd ../service-b
./mvnw clean package
```

## Troubleshooting

### Service Not Starting

1. Check if ports 8081, 8082, and 9090 are available
2. Verify Docker is running
3. Check service logs: `docker-compose logs [service-name]`

### Connection Issues

1. Ensure both services are running: `docker-compose ps`
2. Verify network connectivity: `docker network ls`
3. Check service health endpoints if available

### Build Issues

1. Clean and rebuild: `docker-compose down && docker-compose up -d --build`
2. Remove containers and images: `docker-compose down --rmi all`

## Notes

- Service A depends on Service B and will wait for it to start
- The services communicate over a Docker network named `microservices-network`
- gRPC uses HTTP/2 for efficient communication between services
- The application demonstrates a typical microservices pattern with REST-to-gRPC communication
