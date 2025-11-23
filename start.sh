#!/bin/bash

echo "Starting ..."

# Stop any existing containers
echo "Stopping existing containers..."
docker-compose down

# Start PostgreSQL databases, user-service and trip-service
echo "Starting containers..."
docker-compose up -d --build service-b
docker-compose up -d --build service-a

# Show running containers
echo "Service containers:"
docker-compose ps

echo "Service A started successfully!"
echo "Service B started successfully!"
echo "Application available at: http://localhost:8081, http://localhost:8082"
