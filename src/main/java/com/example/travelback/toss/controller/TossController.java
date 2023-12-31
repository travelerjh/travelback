package com.example.travelback.toss.controller;

import com.example.travelback.toss.domain.HotelToss;
import com.example.travelback.toss.domain.Toss;
import com.example.travelback.toss.domain.TransToss;
import com.example.travelback.toss.service.TossService;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/toss")
public class TossController {

    private final TossService service;

    // 운송 상품 결제 저장
    @PostMapping("transSave")
    public void transSave(TransToss transToss,
                        @SessionAttribute (value = "login",required = false) Member login){
        service.transSave(transToss, login);
    }

    // 호텔 상품 결제 저장
    @PostMapping("hotelSave")
    public void hotelSave(HotelToss hotelToss,
                          @SessionAttribute (value = "login",required = false) Member login){
        service.hotelSave(hotelToss, login);
    }

    // 결제한 운송, 호텔 상품 모두 조회 시키기
    @GetMapping("id/{userId}")
    public Map<String, Object> getTransAndHotelTossByUserId(@PathVariable String userId){
        return service.getTransAndHotelTossByUserId(userId);
    }

    // ------------------- 운송상품 예약번호 저장 로직 -------------------
    @PutMapping("sendAndSave")
    public void sendSmsAndSaveReservation(@RequestParam String tossId,
                                          @RequestParam String reservNumber
                                         ) {
        service.saveReservationNumber(tossId, reservNumber);
    }

    // ------------------- 호텔상품 예약번호 저장 로직 -------------------
    @PutMapping("sendAndSave2")
    public void sendSmsAndSaveReservation2(@RequestParam String hotelTossId,
                                          @RequestParam String reservNumber
    ) {

        service.saveReservationNumber2(hotelTossId, reservNumber);
    }

    @PutMapping("updateTransReservStatus")
    public void updateTransReservStatus(@RequestBody TransToss transToss) {
        System.out.println("reservStatus = " + transToss.getReservStatus());
        System.out.println("tossId = " + transToss.getTossId());
        service.updateTransReservStatus(transToss);
    }

    @PutMapping("updateHotelReservStatus")
    public void updateHotelReservStatus(@RequestBody HotelToss hotelToss) {
        System.out.println("hotelTossStatus = " + hotelToss.getReservStatus());
        System.out.println("hotelTossId = " + hotelToss.getHotelTossId());
        service.updateHotelReservStatus(hotelToss);
    }


}
