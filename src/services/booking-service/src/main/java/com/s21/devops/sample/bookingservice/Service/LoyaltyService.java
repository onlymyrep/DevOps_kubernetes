package com.s21.devops.sample.bookingservice.Service;

import com.s21.devops.sample.bookingservice.Communication.ChargeBalanceReq;
import java.util.UUID;

public interface LoyaltyService {
    Object getLoyaltyBalance(UUID userUid);
    Object chargeBalance(UUID userUid, ChargeBalanceReq chargeBalanceReq);
}