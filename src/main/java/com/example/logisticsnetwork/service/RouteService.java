package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.RouteRequest;
import com.example.logisticsnetwork.entity.Route;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.RouteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RouteService {
 private final RouteRepository repository; private final VehicleService vehicleService; private final DriverService driverService;
 public RouteService(RouteRepository repository,VehicleService vehicleService,DriverService driverService){this.repository=repository;this.vehicleService=vehicleService;this.driverService=driverService;}
 public Route create(RouteRequest r){Route e=new Route();apply(e,r);return repository.save(e);}
 public List<Route> getAll(){return repository.findAllByIsActiveTrue();}
 public Route getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("Route not found: "+id));}
 public Route update(Long id,RouteRequest r){Route e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){Route e=getById(id);e.setIsActive(false);repository.save(e);}
 public Route save(Route e){return repository.save(e);}
 private void apply(Route e,RouteRequest r){e.setRouteDate(r.getRouteDate());e.setOrigin(r.getOrigin());e.setDestination(r.getDestination());e.setStatus(r.getStatus());e.setVehicle(vehicleService.getById(r.getVehicleId()));e.setDriver(driverService.getById(r.getDriverId()));}
}