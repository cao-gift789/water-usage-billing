package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.request.FacilityUpdateRequest;
import com.waterbilling.demo.dto.request.RegisterFacilityRequest;
import com.waterbilling.demo.dto.response.*;
import com.waterbilling.demo.exception.AppException;
import com.waterbilling.demo.exception.ErrorCode;
import com.waterbilling.demo.model.*;
import com.waterbilling.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationManagerRepository locationManagerRepository;

    @Autowired
    private FacilityTypeRepository facilityTypeRepository;

    @Autowired
    private FixedPricingRepository fixedPricingRepository;

    @Autowired
    private PricingTiersRepository pricingTiersRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private WaterMeterRepository waterMeterRepository;

    @Autowired
    private WaterMeterReadingRepository waterMeterReadingRepository;

    @Autowired
    private JoinRequestRepository joinRequestRepository;

    public void updateFacility(Integer facilityId, FacilityUpdateRequest request) {
        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new AppException(ErrorCode.FACILITY_NOT_EXIST));
        FacilityType facilityType = facilityTypeRepository.findById(request.getFacilityTypeId())
                .orElseThrow(() -> new AppException(ErrorCode.FACILITY_TYPE_NOT_EXIST));
        facility.setAddress(request.getAddress());
        facility.setFacilityType(facilityType);
        facilityRepository.save(facility);
    }

    public void deactivateFacility(int facilityId) {
        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new AppException(ErrorCode.FACILITY_NOT_EXIST));
        facility.setIsActive(!facility.getIsActive());
        facilityRepository.save(facility);
    }

    public InvoiceLookupResponse findBillResponses(Integer facilityId) {

        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new AppException(ErrorCode.FACILITY_NOT_EXIST));

        Invoice invoice = invoiceRepository.findLatestInvoice()
                .orElseThrow(() -> new AppException(ErrorCode.INVOICE_NOT_EXIST));
        InvoiceLookupResponse dto = new InvoiceLookupResponse();
        dto.setFacilityId(facility.getFacilityId());
        dto.setCustomerName(facility.getUser().getFullName());
        dto.setStatus(invoice.getStatus().name());
        dto.setAddress(facility.getAddress());
        dto.setCreationDate(invoice.getCreationDate());
        dto.setPaymentDate(invoice.getPaymentDate());
        dto.setTotalPrice(invoice.getTotalAmount());
        dto.setPenaltyFee(invoice.getPenaltyFee());

        BigDecimal totalComsump = new BigDecimal(0);
        Map<String, BigDecimal> waterMeterReadings = new HashMap<>();
        invoice.getWaterMeterReadings()
                .forEach(wr
                        -> {
                    totalComsump.add(wr.getWaterUsage());
                    waterMeterReadings.put(wr.getWaterMeter().getSerialNumber(), wr.getWaterUsage());
                });
        dto.setTotalConsumption(totalComsump);
        dto.setWaterMeterReadingResponses(waterMeterReadings);

        return dto;
    }

    public Set<FacilityOfMineResponse> getListFacility() {

        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByAccountId(Integer.parseInt(accountId))
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Set<LocationManager> locationManagers = user.getLocationManagers();
        Set<Facility> myFacility = user.getFacilities();

        Set<Integer> myFacilityIds = myFacility.stream()
                .map(Facility::getFacilityId)
                .collect(Collectors.toSet());

        return locationManagers.stream()
                .map( locationManager -> {
                    Facility facility = locationManager.getFacility();
                    return FacilityOfMineResponse.builder()
                            .facilityId(facility.getFacilityId())
                            .address(facility.getAddress())
                            .isActive(facility.getIsActive())
                            .registrationDate(facility.getRegistrationDate())
                            .grantedDate(locationManager.getGrantedDate())
                            .isOwner(myFacilityIds.contains(facility.getFacilityId()))
                            .build();
                    }
                )
                .collect(Collectors.toSet());
    }

    @Transactional
    public void createFacilities(RegisterFacilityRequest request){
        Optional<User> optionalUser = userRepository.findByIdentityNumber(request.getIdentityNumber());
        User user = null ;
        user = optionalUser.orElseGet(() -> userRepository.save(
                User.builder()
                        .email(request.getEmail())
                        .phoneNumber(request.getPhoneNumber())
                        .identityNumber(request.getIdentityNumber())
                        .fullName(request.getFullName())
                        .isActive(true)
                        .build()));

        FacilityType facilityType = facilityTypeRepository.findById(request.getFacilityTypeId())
                .orElseThrow(() -> new AppException(ErrorCode.FACILITY_TYPE_NOT_EXIST));
        Facility facility = Facility.builder()
                .facilityType(facilityType)
                .address(request.getAddress())
                .registrationDate(LocalDateTime.now())
                .isActive(true)
                .user(user)
                .build();
        LocationManager locationManager = LocationManager.builder()
                .user(user)
                .facility(facility)
                .grantedDate(LocalDateTime.now())
                .build();
        facilityRepository.save(facility);
        locationManagerRepository.save(locationManager);
    }

    public FacilityDetailResponse getHouseholdDetails(Integer facilityId) {

        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByAccountId(Integer.parseInt(accountId))
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new AppException(ErrorCode.FACILITY_NOT_EXIST));
        boolean isOwner = facility.getUser().getUserId().equals(user.getUserId());
        boolean isMember = locationManagerRepository.findAllByFacility(facility)
                .stream()
                .anyMatch(lm -> lm.getUser().getUserId().equals(user.getUserId()));
        if (!isOwner && !isMember) {
            throw new  AppException(ErrorCode.UNAUTHORIZED);
        }

        FacilityDetailResponse dto = new FacilityDetailResponse();
        dto.setFacilityId(facility.getFacilityId());
        dto.setAddress(facility.getAddress());
        dto.setRegistrationDate(facility.getRegistrationDate());
        dto.setNameOwner(facility.getUser().getFullName());

        // Map facility type
        FacilityType facilityType = facility.getFacilityType();
        FacilityTypeResponse response = new FacilityTypeResponse();
        response.setTypeId(facilityType.getTypeId());
        response.setTypeName(facilityType.getTypeName());
        response.setCalculationMethod(facilityType.getCalculationMethod().name());

        if (facilityType.getCalculationMethod() == FacilityType.CalculationMethod.Fixed) {
            Optional<FixedPricing> fixedPricing = fixedPricingRepository.findByFacilityType(facilityType);

            fixedPricing.ifPresent(pricing -> response.setFixedPrice(pricing.getPricePerM3()));
        } else {
            List<PricingTiers> tiers = pricingTiersRepository.findAllByFacilityType(facilityType);
            response.setTiers(tiers.stream().map(tier -> {
                PricingTierResponse tierResponse = new PricingTierResponse();
                tierResponse.setTierId(tier.getTierId());
                tierResponse.setMinUsage(tier.getMinUsage());
                tierResponse.setMaxUsage(tier.getMaxUsage());
                tierResponse.setPricePerM3(tier.getPricePerM3());
                return tierResponse;
            }).collect(Collectors.toList()));
        }
        // Fetch invoices
        List<InvoiceResponse> invoices = invoiceRepository.findAllByFacility(facility)
                .stream()
                .map(this::mapToInvoiceResponse)
                .collect(Collectors.toList());
        dto.setInvoices(invoices);

        // Fetch water meters and their readings
        List<WaterMeterResponse> waterMeters = waterMeterRepository.findAllByFacility(facility)
                .stream()
                .map(wm -> {
                    WaterMeterResponse wmDto = new WaterMeterResponse();
                    wmDto.setWaterMeterId(wm.getWaterMeterId());
                    wmDto.setSerialNumber(wm.getSerialNumber());
                    List<WaterMeterReadingResponse> readings = waterMeterReadingRepository
                            .findAllByWaterMeter(wm)
                            .stream()
                            .map(this::mapToWaterMeterReadingResponse)
                            .collect(Collectors.toList());
                    wmDto.setReadings(readings);
                    return wmDto;
                })
                .collect(Collectors.toList());
        dto.setWaterMeters(waterMeters);

        // Fetch notifications
        List<NotificationResponse> notifications =
                facility.getNotifications()
                .stream()
                .map(this::mapToNotificationResponse)
                .collect(Collectors.toList());
        dto.setNotifications(notifications);

        if (isOwner) {
            Map<Integer, String> members = new HashMap<>();
            locationManagerRepository.findAllByFacility(facility).forEach(
                    lm -> members.put(lm.getUser().getUserId(),lm.getUser().getFullName()
            ));

            dto.setMembers(members);

            // Fetch join requests
            List<JoinRequestOfFacilityResponse> joinRequests = joinRequestRepository.findAllByFacility(facility)
                    .stream()
                    .map(this::mapToJoinRequestResponse)
                    .collect(Collectors.toList());
            dto.setJoinRequests(joinRequests);
        }
        return dto;
    }

    private InvoiceResponse mapToInvoiceResponse(Invoice invoice) {
        return InvoiceResponse.builder()
                .totalAmount(invoice.getTotalAmount())
                .invoiceId(invoice.getInvoiceId())
                .creationDate(invoice.getCreationDate())
                .penaltyFee(invoice.getPenaltyFee())
                .status(invoice.getStatus().name())
                .build();
    }

    private WaterMeterReadingResponse mapToWaterMeterReadingResponse(WaterMeterReading reading) {
        return WaterMeterReadingResponse.builder()
                .readingId(reading.getReadingId())
                .dateRecorded(reading.getDateRecorded())
                .previousReading(reading.getPreviousReading())
                .currentReading(reading.getCurrentReading())
                .waterUsage(reading.getWaterUsage())
                .build();
    }

    private NotificationResponse mapToNotificationResponse(Notification notification) {
        return NotificationResponse.builder()
                .notificationId(notification.getNotificationId())
                .content(notification.getContent())
                .createdDate(notification.getCreatedDate())
                .title(notification.getTitle())
                .notificationType(notification.getNotificationType().getTypeName())
                .build();
    }

    private JoinRequestOfFacilityResponse mapToJoinRequestResponse(JoinRequest joinRequest) {

        return JoinRequestOfFacilityResponse.builder()
                .requestId(joinRequest.getRequestId())
                .requestDate(joinRequest.getRequestDate())
                .nameUser(joinRequest.getUser().getFullName())
                .userId(joinRequest.getUser().getUserId())
                .build();
    }

}
