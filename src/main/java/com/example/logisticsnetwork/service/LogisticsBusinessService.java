package com.example.logisticsnetwork.service;

import com.example.logisticsnetwork.dto.request.*;
import com.example.logisticsnetwork.entity.*;
import com.example.logisticsnetwork.exception.BusinessRuleException;
import com.example.logisticsnetwork.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogisticsBusinessService {

    private final ShipmentRepository shipmentRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final CustomerRepository customerRepository;
    private final CarrierRepository carrierRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final ShipmentItemRepository shipmentItemRepository;
    private final RouteRepository routeRepository;
    private final DeliveryStopRepository deliveryStopRepository;
    private final TrackingEventRepository trackingEventRepository;
    private final InvoiceRepository invoiceRepository;

    public LogisticsBusinessService(
            ShipmentRepository shipmentRepository,
            InventoryItemRepository inventoryItemRepository,
            ProductRepository productRepository,
            WarehouseRepository warehouseRepository,
            CustomerRepository customerRepository,
            CarrierRepository carrierRepository,
            VehicleRepository vehicleRepository,
            DriverRepository driverRepository,
            ShipmentItemRepository shipmentItemRepository,
            RouteRepository routeRepository,
            DeliveryStopRepository deliveryStopRepository,
            TrackingEventRepository trackingEventRepository,
            InvoiceRepository invoiceRepository) {
        this.shipmentRepository = shipmentRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.customerRepository = customerRepository;
        this.carrierRepository = carrierRepository;
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.shipmentItemRepository = shipmentItemRepository;
        this.routeRepository = routeRepository;
        this.deliveryStopRepository = deliveryStopRepository;
        this.trackingEventRepository = trackingEventRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional
    public Shipment createShipment(ShipmentCreateBusinessRequest request) {
        Warehouse warehouse = warehouseRepository.findByIdAndIsActiveTrue(request.getWarehouseId())
                .orElseThrow(() -> new BusinessRuleException("Warehouse is not available"));
        Customer customer = customerRepository.findByIdAndIsActiveTrue(request.getCustomerId())
                .orElseThrow(() -> new BusinessRuleException("Customer is not available"));

        Shipment shipment = new Shipment();
        shipment.setShipmentDate(request.getShipmentDate());
        shipment.setStatus("CREATED");
        shipment.setWarehouse(warehouse);
        shipment.setCustomer(customer);

        double totalWeight = 0.0;
        List<ShipmentItem> items = new ArrayList<>();

        for (ShipmentLineRequest line : request.getItems()) {
            Product product = productRepository.findByIdAndIsActiveTrue(line.getProductId())
                    .orElseThrow(() -> new BusinessRuleException("Product not found: " + line.getProductId()));

            InventoryItem stock = inventoryItemRepository
                    .findByWarehouseIdAndProductIdAndIsActiveTrue(request.getWarehouseId(), line.getProductId())
                    .orElseThrow(() -> new BusinessRuleException("Product is not stocked in this warehouse: " + line.getProductId()));

            if (stock.getQuantity() < line.getQuantity()) {
                throw new BusinessRuleException("Insufficient stock for product: " + product.getName());
            }

            stock.setQuantity(stock.getQuantity() - line.getQuantity());
            inventoryItemRepository.save(stock);

            ShipmentItem shipmentItem = new ShipmentItem();
            shipmentItem.setShipment(shipment);
            shipmentItem.setProduct(product);
            shipmentItem.setQuantity(line.getQuantity());
            items.add(shipmentItem);

            totalWeight += product.getWeightKg() * line.getQuantity();
        }

        shipment.setTotalWeight(totalWeight);
        Shipment saved = shipmentRepository.save(shipment);
        shipmentItemRepository.saveAll(items);
        saved.setShipmentItems(items);
        return saved;
    }

    @Transactional
    public Shipment assignShipment( Long shipmentId, AssignShipmentRequest request) {
        Shipment shipment = getShipment(shipmentId);
        Carrier carrier = carrierRepository.findByIdAndIsActiveTrue(request.getCarrierId())
                .orElseThrow(() -> new BusinessRuleException("Carrier is not available"));
        Vehicle vehicle = vehicleRepository.findByIdAndIsActiveTrue(request.getVehicleId())
                .orElseThrow(() -> new BusinessRuleException("Vehicle is not available"));
        Driver driver = driverRepository.findByIdAndIsActiveTrue(request.getDriverId())
                .orElseThrow(() -> new BusinessRuleException("Driver is not available"));

        if (!vehicle.getCarrier().getId().equals(carrier.getId())) {
            throw new BusinessRuleException("Vehicle does not belong to selected carrier");
        }
        if (!driver.getCarrier().getId().equals(carrier.getId())) {
            throw new BusinessRuleException("Driver does not belong to selected carrier");
        }
        if (!"AVAILABLE".equalsIgnoreCase(vehicle.getStatus())) {
            throw new BusinessRuleException("Vehicle is not available");
        }
        if (!"AVAILABLE".equalsIgnoreCase(driver.getStatus())) {
            throw new BusinessRuleException("Driver is not available");
        }
        if (shipment.getTotalWeight() > vehicle.getCapacityKg()) {
            throw new BusinessRuleException("Shipment exceeds vehicle capacity");
        }

        shipment.setCarrier(carrier);
        shipment.setStatus("ASSIGNED");
        vehicle.setStatus("ASSIGNED");
        driver.setStatus("ASSIGNED");

        vehicleRepository.save(vehicle);
        driverRepository.save(driver);
        return shipmentRepository.save(shipment);
    }

    @Transactional
    public Route buildRoute(RouteBuildRequest request) {
        Shipment shipment = getShipment(request.getShipmentId());
        Vehicle vehicle = vehicleRepository.findByIdAndIsActiveTrue(request.getVehicleId())
                .orElseThrow(() -> new BusinessRuleException("Vehicle is not available"));
        Driver driver = driverRepository.findByIdAndIsActiveTrue(request.getDriverId())
                .orElseThrow(() -> new BusinessRuleException("Driver is not available"));

        if (shipment.getCarrier() == null) {
            throw new BusinessRuleException("Shipment must be assigned to a carrier first");
        }
        if (!vehicle.getCarrier().getId().equals(shipment.getCarrier().getId())) {
            throw new BusinessRuleException("Vehicle carrier does not match shipment carrier");
        }
        if (!driver.getCarrier().getId().equals(shipment.getCarrier().getId())) {
            throw new BusinessRuleException("Driver carrier does not match shipment carrier");
        }
        if (shipment.getTotalWeight() > vehicle.getCapacityKg()) {
            throw new BusinessRuleException("Shipment exceeds vehicle capacity");
        }

        Route route = new Route();
        route.setRouteDate(request.getRouteDate());
        route.setOrigin(request.getOrigin());
        route.setDestination(request.getDestination());
        route.setStatus("PLANNED");
        route.setVehicle(vehicle);
        route.setDriver(driver);
        return routeRepository.save(route);
    }

    @Transactional
    public DeliveryStop addDeliveryStop(DeliveryStopAddRequest request) {
        Route route = routeRepository.findByIdAndIsActiveTrue(request.getRouteId())
                .orElseThrow(() -> new BusinessRuleException("Route is not available"));
        Shipment shipment = getShipment(request.getShipmentId());

        if (deliveryStopRepository.existsByRouteIdAndSequenceAndIsActiveTrue(route.getId(), request.getSequence())) {
            throw new BusinessRuleException("Duplicate delivery-stop sequence on this route");
        }

        DeliveryStop stop = new DeliveryStop();
        stop.setRoute(route);
        stop.setShipment(shipment);
        stop.setSequence(request.getSequence());
        stop.setAddress(request.getAddress());
        stop.setEta(request.getEta());
        stop.setStatus("PENDING");
        return deliveryStopRepository.save(stop);
    }

    @Transactional
    public TrackingEvent appendTrackingEvent(TrackingAppendRequest request) {
        Shipment shipment = getShipment(request.getShipmentId());

        TrackingEvent event = new TrackingEvent();
        event.setShipment(shipment);
        event.setEventTime(LocalDateTime.now());
        event.setLocation(request.getLocation());
        event.setStatus(request.getStatus());
        event.setNote(request.getNote());

        shipment.setStatus(request.getStatus());
        shipmentRepository.save(shipment);
        return trackingEventRepository.save(event);
    }

    @Transactional
    public DeliveryStop completeDeliveryStop(Long stopId) {
        DeliveryStop stop = deliveryStopRepository.findByIdAndIsActiveTrue(stopId)
                .orElseThrow(() -> new BusinessRuleException("Delivery stop is not available"));

        stop.setStatus("COMPLETE");
        DeliveryStop saved = deliveryStopRepository.save(stop);

        List<DeliveryStop> routeStops = deliveryStopRepository.findAllByRouteIdAndIsActiveTrue(stop.getRoute().getId());
        boolean allComplete = !routeStops.isEmpty()
                && routeStops.stream().allMatch(s -> "COMPLETE".equalsIgnoreCase(s.getStatus()));

        if (allComplete) {
            Route route = stop.getRoute();
            route.setStatus("COMPLETE");
            routeRepository.save(route);

            Vehicle vehicle = route.getVehicle();
            Driver driver = route.getDriver();
            vehicle.setStatus("AVAILABLE");
            driver.setStatus("AVAILABLE");
            vehicleRepository.save(vehicle);
            driverRepository.save(driver);
        }

        return saved;
    }

    @Transactional
    public Invoice generateInvoice(InvoiceGenerateRequest request) {
        Shipment shipment = getShipment(request.getShipmentId());

        if (!"DELIVERED".equalsIgnoreCase(shipment.getStatus())) {
            throw new BusinessRuleException("Invoice can be generated only for a delivered shipment");
        }
        if (invoiceRepository.existsByShipmentIdAndIsActiveTrue(shipment.getId())) {
            throw new BusinessRuleException("An active invoice already exists for this shipment");
        }

        Invoice invoice = new Invoice();
        invoice.setShipment(shipment);
        invoice.setCustomer(shipment.getCustomer());
        invoice.setAmount(request.getAmount());
        invoice.setIssuedDate(LocalDate.now());
        invoice.setStatus("UNPAID");
        return invoiceRepository.save(invoice);
    }

    private Shipment getShipment(Long id) {
        return shipmentRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new BusinessRuleException("Shipment is not available"));
    }
}
