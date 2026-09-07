# Project 6 — Logistics & Freight Network System

Spring Boot REST API for a freight and logistics network.

## Stack
- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA / Hibernate
- Spring Validation
- MySQL
- Lombok
- Maven
- Postman

## Main domain
Warehouse, Product, InventoryItem, Customer, Address, Shipment, ShipmentItem, Carrier,
Vehicle, Driver, Route, DeliveryStop, TrackingEvent, Invoice, Staff and ServiceZone.

## Requirements implemented
- Shared BaseClass with id, isActive, createdDate and updatedDate
- Soft delete
- DTO-only API responses
- Request DTO validation
- Global exception handling
- CRUD endpoints for all entities
- Shipment/inventory workflow
- Carrier, vehicle and driver assignment checks
- Route and delivery stop workflow
- Tracking-event workflow
- Invoice workflow
- Required custom queries
- Warehouse, carrier and customer statistics
- Postman collection for API testing

## Database configuration
Run MySQL locally and configure these environment variables when needed:

- DB_URL
- DB_USERNAME
- DB_PASSWORD
- SERVER_PORT

Defaults are defined in `src/main/resources/application.properties`.

Example database URL:

`jdbc:mysql://localhost:3306/logistics_network?createDatabaseIfNotExist=true`

## Run Spring Boot
```bash
mvn spring-boot:run
```

The default API base URL is:

`http://localhost:8080`

## Test with Postman

Import this collection into Postman:

`postman/Project6_Logistics_Network.postman_collection.json`

The collection contains:
- CRUD requests for all 16 entities
- Shipment business workflow
- Carrier/vehicle/driver assignment
- Route and delivery-stop operations
- Tracking events
- Invoice generation
- Required custom queries
- Statistics
- Error test cases

Recommended execution order is documented in:

`docs/POSTMAN_TEST_ORDER.md`

## API groups
- `/api/warehouses`
- `/api/products`
- `/api/inventory-items`
- `/api/customers`
- `/api/addresses`
- `/api/carriers`
- `/api/shipments`
- `/api/shipment-items`
- `/api/vehicles`
- `/api/drivers`
- `/api/routes`
- `/api/delivery-stops`
- `/api/tracking-events`
- `/api/invoices`
- `/api/staff`
- `/api/service-zones`
- `/api/business`
- `/api/queries`
- `/api/stats`

## Error handling
Errors are returned using the custom `ErrorResponse` record with status, error, message and timestamp.
