package com.drop.dropshop.deliveryAdmin.service;

import com.drop.dropshop.deliveryAdmin.dto.AnnounceLogDto;
import com.drop.dropshop.deliveryAdmin.dto.DeliveryStatusDto;
import com.drop.dropshop.deliveryAdmin.entity.AnnounceLog;
import com.drop.dropshop.deliveryAdmin.entity.DeliveryStatus;
import com.drop.dropshop.deliveryAdmin.repository.AnnounceLogRepository;
import com.drop.dropshop.deliveryAdmin.repository.DeliveryStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AnnounceLogService {
    private final AnnounceLogRepository announceLogRepository;

    @Autowired
    public AnnounceLogService(AnnounceLogRepository announceLogRepository){ this.announceLogRepository = announceLogRepository;}

    @Transactional
    public void createAnnounceLog(AnnounceLogDto requestDto){
        // requestDto에서 current값만 utill의 NaverGeo를 이용해 변환된 값만 넣어줌
        AnnounceLog announceLog= new AnnounceLog(requestDto);
        this.announceLogRepository.save(announceLog);
    }
}
