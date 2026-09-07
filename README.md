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

## Database configuration
The application reads configuration from environment variables:

- DB_URL
- DB_USERNAME
- DB_PASSWORD
- SERVER_PORT

Defaults are defined in `src/main/resources/application.properties`.

Example database URL:

`jdbc:mysql://localhost:3306/logistics_network?createDatabaseIfNotExist=true`

## Run
```bash
mvn spring-boot:run
```

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

## Important business endpoints
- `POST /api/business/shipments`
- `PUT /api/business/shipments/{shipmentId}/assign`
- `POST /api/business/routes`
- `POST /api/business/delivery-stops`
- `POST /api/business/tracking-events`
- `PUT /api/business/delivery-stops/{stopId}/complete`
- `POST /api/business/invoices`

## Custom queries
- Shipments by status
- Inventory below threshold
- Routes for a driver on a date
- Available vehicles
- Customer shipment history
- Customer unpaid invoices

## Error handling
Errors are returned using the custom `ErrorResponse` record with status, error, message and timestamp.
