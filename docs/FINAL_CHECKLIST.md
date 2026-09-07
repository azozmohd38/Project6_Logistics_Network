# Final Requirement Checklist

This checklist maps the project brief to the implementation in this repository.

## Ground rules

- [x] Shared `BaseClass` with `id`, `isActive`, `createdDate`, and `updatedDate`
- [x] Read operations filter inactive records
- [x] Delete operations perform soft delete
- [x] Create/update controllers use `@Valid @RequestBody`
- [x] Controllers return DTOs rather than raw entities
- [x] DTOs use Lombok Builder
- [x] Validation limits align with entity column lengths
- [x] Debugging log included
- [x] Cyclic JSON exposure is avoided through DTO responses and ignored back-references
- [x] No credentials are committed

## Phase 1 — Entities and relationships

Implemented entities:

1. Warehouse
2. Product
3. InventoryItem
4. Customer
5. Address
6. Shipment
7. ShipmentItem
8. Carrier
9. Vehicle
10. Driver
11. Route
12. DeliveryStop
13. TrackingEvent
14. Invoice
15. Staff
16. ServiceZone

Required relationship chains are represented in the entity mappings and documented in `docs/schema.mmd`.

## Phase 2 — Repositories, services and CRUD

- [x] One repository per entity
- [x] Active-only repository reads
- [x] One service per entity
- [x] Create
- [x] Get all
- [x] Get by id
- [x] Update
- [x] Soft delete
- [x] One REST controller per entity
- [x] Full CRUD Postman requests for all 16 resources

## Phase 3 — DTOs

- [x] One response DTO per entity
- [x] Lombok Builder used
- [x] Single-entity conversion helper
- [x] List conversion helper
- [x] Sensitive/contact fields omitted where not needed
- [x] Controllers return DTOs only

## Phase 4 — Business operations

- [x] Create shipment for customer from warehouse
- [x] Add shipment items
- [x] Decrement warehouse inventory
- [x] Reject insufficient stock
- [x] Assign carrier
- [x] Validate vehicle availability
- [x] Validate driver availability
- [x] Validate vehicle carrier ownership
- [x] Validate driver carrier ownership
- [x] Reject over-capacity shipment
- [x] Build route
- [x] Add delivery stops
- [x] Reject duplicate sequence on same route
- [x] Append tracking events
- [x] Update shipment status from tracking event
- [x] Complete delivery stop
- [x] Auto-complete route when all active stops are complete
- [x] Release vehicle and driver when route completes
- [x] Generate invoice only for delivered shipment
- [x] Reject duplicate active invoice
- [x] List unpaid invoices for customer

## Required custom queries

- [x] Shipments by status
- [x] Inventory below reorder threshold
- [x] Routes for driver on date
- [x] Currently available vehicles
- [x] Customer shipment history

## Required statistics

- [x] Warehouse active shipments
- [x] Warehouse total inventory units
- [x] Carrier vehicle count
- [x] Carrier driver count
- [x] Carrier active routes
- [x] Customer total invoiced amount

## Phase 5 — Validation and errors

- [x] Spring Validation dependency
- [x] `@NotBlank`
- [x] `@Size`
- [x] `@Email`
- [x] `@Positive`
- [x] Date validation
- [x] `ResourceNotFoundException`
- [x] Record-based `ErrorResponse`
- [x] Global `@RestControllerAdvice`
- [x] Validation error handler
- [x] Business-rule error handler
- [x] Data-integrity error handler
- [x] Generic fallback handler

## Testing and deliverables

- [x] Maven unit tests
- [x] Spring context test using H2
- [x] GitHub Actions CI
- [x] MySQL startup smoke-test CI job
- [x] Full Postman collection
- [x] Error-case examples
- [x] Debugging log
- [x] Schema relationship diagram source
- [x] Schema verification instructions
- [x] Docker Compose MySQL setup

## Manual submission artifact

The project brief specifically asks for screenshots of the Hibernate-generated MySQL schema. The application and CI verify schema generation, and `docs/SCHEMA.md` explains exactly what to capture. A true MySQL Workbench screenshot must be captured from a running graphical Workbench session; it is not fabricated in this repository.
