package com.waterbilling.demo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.waterbilling.demo.model.Invoice.InvoiceStatus;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class InvoiceDetailResponse {
	 Integer id;
	 Integer householdCode;
	 LocalDateTime period;
	 InvoiceStatus  status;
	 String customerName;
	 Integer customerCode;
	 String address;
	 Set<MetersRespone>meters;
	 BigDecimal totalConsumption;
	 BigDecimal waterCost;	
	 LocalDateTime dueDate;
	 Set<String> MethodName;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
