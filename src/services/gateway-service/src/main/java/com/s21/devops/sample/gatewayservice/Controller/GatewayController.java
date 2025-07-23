package com.s21.devops.sample.gatewayservice.Controller;

import com.s21.devops.sample.gatewayservice.Communication.*;
import com.s21.devops.sample.gatewayservice.Exception.*;
import com.s21.devops.sample.gatewayservice.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/gateway")
public class GatewayController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private HotelsService hotelsService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private LoyaltyService loyaltyService;

    @Autowired
    private ReportService reportService;

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody CreateUserReq createUserReq) {
        sessionService.createUser(createUserReq);
    }

    @GetMapping("/hotels")
    public HotelInfoRes[] getHotels() {
        return hotelsService.getAllHotels();
    }

    @GetMapping("/hotels/{hotelUid}")
    public HotelInfoRes getHotelInfo(@PathVariable UUID hotelUid) 
            throws HotelNotFoundException {
        return hotelsService.getHotel(hotelUid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/booking")
    public void bookHotel(@Valid @RequestBody BookHotelReq bookHotelReq) {
        bookingService.bookHotel(bookHotelReq);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/booking/{hotelUid}")
    public void removeBooking(@PathVariable UUID hotelUid) 
            throws ReservationNotFoundException {
        bookingService.removeBooking(hotelUid);
    }

    @GetMapping("/booking/{hotelUid}")
    public BookingInfo getBookingInfo(@PathVariable UUID hotelUid) 
            throws ReservationNotFoundException {
        return bookingService.getBookingInfo(hotelUid);
    }

    @GetMapping("/booking")
    public BookingInfo[] getAllBookingInfo() {
        return bookingService.getAllBookingInfo();
    }

    @GetMapping("/booking/{hotelUid}/rooms")
    public HotelsAvailabilityRes getBookingAvailability(
            @PathVariable UUID hotelUid, 
            @RequestParam String from, 
            @RequestParam String to) 
            throws HotelNotFoundException {
        return bookingService.getHotelsAvailability(hotelUid, from, to);
    }

    @GetMapping("/loyalty")
    public LoyaltyBalanceRes getLoyaltyBalance() {
        try {
            return loyaltyService.getLoyaltyBalance();
        } catch (LoyaltyNotFoundException ex) {
            return LoyaltyBalanceRes.loyaltyBalanceResFromParams("NO", 0.0);
        }
    }

    @PostMapping("/hotels")
    public ResponseEntity<Void> addHotel(@Valid @RequestBody CreateHotelReq createHotelReq) 
            throws HotelAlreadyExistsException {
        UUID hotelUid = hotelsService.createHotel(createHotelReq);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{hotelUid}")
                .buildAndExpand(hotelUid)
                .toUri()
        ).build();
    }

    @PatchMapping("/hotels/{hotelUid}/rooms")
    public void patchRoomsInfo(
            @PathVariable UUID hotelUid, 
            @Valid @RequestBody PatchRoomsInfoReq patchRoomsInfoReq) {
        bookingService.patchRoomInfo(hotelUid, patchRoomsInfoReq);
    }

    @GetMapping("/reports/booking")
    public BookingStatisticsMessage[] getBookingStats(
            @RequestParam("from") String from, 
            @RequestParam("to") String to) {
        return reportService.getUserStatistics(from, to);
    }

    @GetMapping("/reports/hotels-filling")
    public HotelFillingStatistics[] getFillingStats(
            @RequestParam("from") String from, 
            @RequestParam("to") String to) {
        return reportService.getHotelStatistics(from, to);
    }
}