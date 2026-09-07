package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.Customer;
import lombok.*;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class CustomerDTO {
 private Long id; private String name; private String type;
 public static CustomerDTO convertToDTO(Customer e){return CustomerDTO.builder().id(e.getId()).name(e.getName()).type(e.getType()).build();}
 public static List<CustomerDTO> convertToDTO(List<Customer> list){return list.stream().map(CustomerDTO::convertToDTO).toList();}
}