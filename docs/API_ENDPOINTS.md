# API Endpoint Reference

Every standard entity controller supports:

- `POST /api/{resource}`
- `GET /api/{resource}`
- `GET /api/{resource}/{id}`
- `PUT /api/{resource}/{id}`
- `DELETE /api/{resource}/{id}`

Delete operations are soft deletes.

## Business operations

### Create shipment and decrement inventory
`POST /api/business/shipments`

### Assign shipment to carrier, vehicle and driver
`PUT /api/business/shipments/{shipmentId}/assign`

### Build route
`POST /api/business/routes`

### Add route delivery stop
`POST /api/business/delivery-stops`

### Append shipment tracking event
`POST /api/business/tracking-events`

### Complete stop and auto-complete route
`PUT /api/business/delivery-stops/{stopId}/complete`

### Generate invoice
`POST /api/business/invoices`

## Required custom queries

- `GET /api/queries/shipments/by-status?status=IN_TRANSIT`
- `GET /api/queries/inventory/below-threshold?threshold=10`
- `GET /api/queries/routes/by-driver-date?driverId=1&routeDate=2026-09-07`
- `GET /api/queries/vehicles/available`
- `GET /api/queries/customers/{customerId}/shipment-history`
- `GET /api/queries/customers/{customerId}/unpaid-invoices`

## Statistics

- `GET /api/stats/warehouses/{warehouseId}`
- `GET /api/stats/carriers/{carrierId}`
- `GET /api/stats/customers/{customerId}`
