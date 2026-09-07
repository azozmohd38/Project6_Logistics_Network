package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.TrackingEventRequest;
import com.example.logisticsnetwork.entity.TrackingEvent;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.TrackingEventRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TrackingEventService {
 private final TrackingEventRepository repository; private final ShipmentService shipmentService;
 public TrackingEventService(TrackingEventRepository repository,ShipmentService shipmentService){this.repository=repository;this.shipmentService=shipmentService;}
 public TrackingEvent create(TrackingEventRequest r){TrackingEvent e=new TrackingEvent();apply(e,r);return repository.save(e);}
 public List<TrackingEvent> getAll(){return repository.findAllByIsActiveTrue();}
 public TrackingEvent getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("TrackingEvent not found: "+id));}
 public TrackingEvent update(Long id,TrackingEventRequest r){TrackingEvent e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){TrackingEvent e=getById(id);e.setIsActive(false);repository.save(e);}
 private void apply(TrackingEvent e,TrackingEventRequest r){e.setEventTime(r.getEventTime());e.setLocation(r.getLocation());e.setStatus(r.getStatus());e.setNote(r.getNote());e.setShipment(shipmentService.getById(r.getShipmentId()));}
}