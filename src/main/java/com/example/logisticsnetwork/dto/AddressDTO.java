package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.Address;
import lombok.*;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class AddressDTO {
 private Long id; private String street; private String city; private String postalCode; private String country; private Long customerId;
 public static AddressDTO convertToDTO(Address e){return AddressDTO.builder().id(e.getId()).street(e.getStreet()).city(e.getCity()).postalCode(e.getPostalCode()).country(e.getCountry()).customerId(e.getCustomer().getId()).build();}
 public static List<AddressDTO> convertToDTO(List<Address> list){return list.stream().map(AddressDTO::convertToDTO).toList();}
}