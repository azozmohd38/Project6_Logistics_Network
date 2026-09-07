# Recommended Postman Test Order

Run these in order so foreign-key records exist before dependent requests.

1. Create Warehouse
2. Create Product
3. Create Customer
4. Create ServiceZone
5. Create Address
6. Create Carrier
7. Create Vehicle with status AVAILABLE
8. Create Driver with status AVAILABLE
9. Create InventoryItem with sufficient quantity
10. Create shipment through `POST /api/business/shipments`
11. Assign shipment to carrier, vehicle and driver
12. Build a route
13. Add delivery stops with unique sequence values
14. Append PICKED_UP tracking event
15. Append IN_TRANSIT tracking event
16. Append DELIVERED tracking event
17. Complete every delivery stop
18. Generate invoice
19. Query unpaid invoices
20. Run warehouse, carrier and customer statistics

## Negative cases

- Unknown entity id
- Blank required fields
- Invalid email
- Zero or negative capacity
- Out-of-stock shipment
- Vehicle over capacity
- Vehicle unavailable
- Driver unavailable
- Vehicle from wrong carrier
- Driver from wrong carrier
- Duplicate delivery-stop sequence
- Invoice before shipment is delivered
- Duplicate invoice for a shipment
