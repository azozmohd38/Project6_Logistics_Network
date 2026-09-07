# Database Schema

The project uses Hibernate/JPA to generate the MySQL schema from the entity mappings.

## Main relationship chains

- Warehouse -> InventoryItem -> Product
- Warehouse -> Shipment -> ShipmentItem -> Product
- Customer -> Shipment
- Customer -> Address -> ServiceZone
- Carrier -> Shipment
- Carrier -> Vehicle -> Route
- Carrier -> Driver -> Route
- Route -> DeliveryStop -> Shipment
- Shipment -> TrackingEvent
- Shipment -> Invoice -> Customer
- Warehouse -> Staff

## Schema verification checklist

After starting MySQL and the Spring Boot application:

1. Open MySQL Workbench.
2. Refresh the `logistics_network` schema.
3. Confirm all 16 entity tables exist.
4. Confirm foreign keys for `shipment_item.shipment_id` and `shipment_item.product_id`.
5. Confirm foreign keys for `delivery_stop.route_id` and `delivery_stop.shipment_id`.
6. Confirm unique constraints for warehouse/product inventory and route/sequence delivery stops.
7. Confirm `invoice.shipment_id` is unique.
8. Confirm `service_zone_id` exists on the address table.
9. Capture the schema diagram as the required submission screenshot.

The Mermaid source is stored in `docs/schema.mmd` and provides a repository-side relationship reference. The actual submission screenshot should still be captured from the Hibernate-generated MySQL schema.
