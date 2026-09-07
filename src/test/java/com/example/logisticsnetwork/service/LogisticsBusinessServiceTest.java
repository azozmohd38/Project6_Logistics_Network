package com.example.logisticsnetwork.service;

import com.example.logisticsnetwork.dto.request.*;
import com.example.logisticsnetwork.entity.*;
import com.example.logisticsnetwork.exception.BusinessRuleException;
import com.example.logisticsnetwork.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogisticsBusinessServiceTest {

    private ShipmentRepository shipmentRepository;
    private InventoryItemRepository inventoryItemRepository;
    private ProductRepository productRepository;
    private WarehouseRepository warehouseRepository;
    private CustomerRepository customerRepository;
    private CarrierRepository carrierRepository;
    private VehicleRepository vehicleRepository;
    private DriverRepository driverRepository;
    private ShipmentItemRepository shipmentItemRepository;
    private RouteRepository routeRepository;
    private DeliveryStopRepository deliveryStopRepository;
    private TrackingEventRepository trackingEventRepository;
    private InvoiceRepository invoiceRepository;
    private LogisticsBusinessService service;

    @BeforeEach
    void setUp() {
        shipmentRepository = mock(ShipmentRepository.class);
        inventoryItemRepository = mock(InventoryItemRepository.class);
        productRepository = mock(ProductRepository.class);
        warehouseRepository = mock(WarehouseRepository.class);
        customerRepository = mock(CustomerRepository.class);
        carrierRepository = mock(CarrierRepository.class);
        vehicleRepository = mock(VehicleRepository.class);
        driverRepository = mock(DriverRepository.class);
        shipmentItemRepository = mock(ShipmentItemRepository.class);
        routeRepository = mock(RouteRepository.class);
        deliveryStopRepository = mock(DeliveryStopRepository.class);
        trackingEventRepository = mock(TrackingEventRepository.class);
        invoiceRepository = mock(InvoiceRepository.class);

        service = new LogisticsBusinessService(
                shipmentRepository,
                inventoryItemRepository,
                productRepository,
                warehouseRepository,
                customerRepository,
                carrierRepository,
                vehicleRepository,
                driverRepository,
                shipmentItemRepository,
                routeRepository,
                deliveryStopRepository,
                trackingEventRepository,
                invoiceRepository
        );
    }

    @Test
    void createShipmentRejectsOutOfStockProduct() {
        Warehouse warehouse = new Warehouse();
        Customer customer = new Customer();
        Product product = new Product();
        product.setName("Heavy Box");
        product.setWeightKg(5.0);

        InventoryItem stock = new InventoryItem();
        stock.setQuantity(2);

        when(warehouseRepository.findByIdAndIsActiveTrue(1L)).thenReturn(Optional.of(warehouse));
        when(customerRepository.findByIdAndIsActiveTrue(2L)).thenReturn(Optional.of(customer));
        when(productRepository.findByIdAndIsActiveTrue(3L)).thenReturn(Optional.of(product));
        when(inventoryItemRepository.findByWarehouseIdAndProductIdAndIsActiveTrue(1L, 3L))
                .thenReturn(Optional.of(stock));

        ShipmentLineRequest line = new ShipmentLineRequest(3L, 5);
        ShipmentCreateBusinessRequest request =
                new ShipmentCreateBusinessRequest(1L, 2L, LocalDate.now(), List.of(line));

        assertThrows(BusinessRuleException.class, () -> service.createShipment(request));
        verify(shipmentRepository, never()).save(any());
    }

    @Test
    void assignShipmentRejectsVehicleOverCapacity() {
        Shipment shipment = new Shipment();
        shipment.setTotalWeight(1000.0);

        Carrier carrier = new Carrier();
        carrier.setId(10L);

        Vehicle vehicle = new Vehicle();
        vehicle.setCarrier(carrier);
        vehicle.setCapacityKg(500.0);
        vehicle.setStatus("AVAILABLE");

        Driver driver = new Driver();
        driver.setCarrier(carrier);
        driver.setStatus("AVAILABLE");

        when(shipmentRepository.findByIdAndIsActiveTrue(1L)).thenReturn(Optional.of(shipment));
        when(carrierRepository.findByIdAndIsActiveTrue(10L)).thenReturn(Optional.of(carrier));
        when(vehicleRepository.findByIdAndIsActiveTrue(20L)).thenReturn(Optional.of(vehicle));
        when(driverRepository.findByIdAndIsActiveTrue(30L)).thenReturn(Optional.of(driver));

        AssignShipmentRequest request = new AssignShipmentRequest(10L, 20L, 30L);

        assertThrows(BusinessRuleException.class, () -> service.assignShipment(1L, request));
    }

    @Test
    void addDeliveryStopRejectsDuplicateSequence() {
        Route route = new Route();
        route.setId(4L);
        Shipment shipment = new Shipment();

        when(routeRepository.findByIdAndIsActiveTrue(4L)).thenReturn(Optional.of(route));
        when(shipmentRepository.findByIdAndIsActiveTrue(5L)).thenReturn(Optional.of(shipment));
        when(deliveryStopRepository.existsByRouteIdAndSequenceAndIsActiveTrue(4L, 1))
                .thenReturn(true);

        DeliveryStopAddRequest request = new DeliveryStopAddRequest(4L, 5L, 1, "Muscat", null);

        assertThrows(BusinessRuleException.class, () -> service.addDeliveryStop(request));
    }

    @Test
    void generateInvoiceRejectsUndeliveredShipment() {
        Shipment shipment = new Shipment();
        shipment.setStatus("IN_TRANSIT");

        when(shipmentRepository.findByIdAndIsActiveTrue(7L)).thenReturn(Optional.of(shipment));

        InvoiceGenerateRequest request = new InvoiceGenerateRequest(7L, new BigDecimal("50.00"));

        assertThrows(BusinessRuleException.class, () -> service.generateInvoice(request));
        verify(invoiceRepository, never()).save(any());
    }
}
