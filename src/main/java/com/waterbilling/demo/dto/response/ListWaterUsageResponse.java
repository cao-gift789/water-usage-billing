package com.waterbilling.demo.dto.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
public class ListWaterUsageResponse {
	 Set<WaterUsageResponse> waterUsageResponses;
	 BigDecimal medium;
	 BigDecimal total;
	
	
	
	
	
}
