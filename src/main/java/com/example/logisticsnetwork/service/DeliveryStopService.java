package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.DeliveryStopRequest;
import com.example.logisticsnetwork.entity.DeliveryStop;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.DeliveryStopRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DeliveryStopService {
 private final DeliveryStopRepository repository; private final RouteService routeService; private final ShipmentService shipmentService;
 public DeliveryStopService(DeliveryStopRepository repository,RouteService routeService,ShipmentService shipmentService){this.repository=repository;this.routeService=routeService;this.shipmentService=shipmentService;}
 public DeliveryStop create(DeliveryStopRequest r){DeliveryStop e=new DeliveryStop();apply(e,r);return repository.save(e);}
 public List<DeliveryStop> getAll(){return repository.findAllByIsActiveTrue();}
 public DeliveryStop getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("DeliveryStop not found: "+id));}
 public DeliveryStop update(Long id,DeliveryStopRequest r){DeliveryStop e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){DeliveryStop e=getById(id);e.setIsActive(false);repository.save(e);}
 public DeliveryStop save(DeliveryStop e){return repository.save(e);}
 private void apply(DeliveryStop e,DeliveryStopRequest r){e.setSequence(r.getSequence());e.setAddress(r.getAddress());e.setStatus(r.getStatus());e.setEta(r.getEta());e.setRoute(routeService.getById(r.getRouteId()));e.setShipment(shipmentService.getById(r.getShipmentId()));}
}