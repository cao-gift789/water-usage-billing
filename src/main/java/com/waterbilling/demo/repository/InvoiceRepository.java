package com.waterbilling.demo.repository;

import com.waterbilling.demo.dto.response.InvoiceResponse;
import com.waterbilling.demo.model.Account;
import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.Invoice;
import com.waterbilling.demo.model.Invoice.InvoiceStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	
    List<Invoice> findAllByFacility(Facility facility);

    @Query("SELECT i FROM Invoice i WHERE i.creationDate = (SELECT MAX(i2.creationDate) FROM Invoice i2)")
    Optional<Invoice> findLatestInvoice();


//    @Query("SELECT new com.waterbilling.demo.dto.response.InvoiceResponse(i.invoiceId, i.totalAmount, i.status,  i.creationDate, i.penaltyFee) " +
//            "FROM Invoice i " +
//            "WHERE (:search IS NULL OR i.totalAmount LIKE CONCAT('%', :search, '%')) " +
//            "AND (:status IS NULL OR i.status = :status) " +
//            "AND (:facilityId IS NULL OR i.facility.facilityId = :facilityId) " +
//            "AND (:creationDate IS NULL OR i.creationDate = :creationDate) " )
//    Page<InvoiceResponse> findAllInvoices(@Param("search") String search,
//                                     @Param("status") String status,
//                                     @Param("facilityId") Integer facilityId,
//                                     @Param("creationDate") LocalDateTime creationDate,
//                                     Pageable pageable);

    @Query("SELECT COUNT(i) > 0 FROM Invoice i " +
            "WHERE i.facility.facilityId = :facilityId " +
            "AND FUNCTION('MONTH', i.creationDate) = FUNCTION('MONTH', CURRENT_DATE) " +
            "AND FUNCTION('YEAR', i.creationDate) = FUNCTION('YEAR', CURRENT_DATE)")
    boolean existsInvoiceForThisMonth(@Param("facilityId") int facilityId);
}
