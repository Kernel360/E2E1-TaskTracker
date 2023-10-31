package org.fastcampus.proejct.global.service;

import org.fastcampus.proejct.global.domain.NotificationEntity;
import org.fastcampus.proejct.global.dto.NotificationDto;
import org.fastcampus.proejct.global.repository.NotificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * @className    : NotificationService
 * @fileName     : NotificationService.java
 * @author       : Jiyong Jung
 * @date         : 2023-10-30 :: 오후 4:29
 * @desc         : 알림 기능 CRUD
 * ===========================================================
 * line                  AUTHOR             NOTE
 * -----------------------------------------------------------
 *   0              Jiyong Jung        최초 생성
 *   39             Jiyong Jung        args NotificationDTO 받도록 수정
 */
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void getAllNotice(){

    }

    public void getNotice(){
        //notificationRepository.save();
    }

    /**
     * @param NotificationDto param
     * @return boolean
     */
    public boolean addNotice(NotificationDto param){
        boolean ret = false;
        try{
            ModelMapper mapper = new ModelMapper();
            NotificationEntity notice = mapper.map(param,NotificationEntity.class);
            notificationRepository.save(notice);
            ret = true;
        }catch (Exception e){
            ret = false;
            e.printStackTrace();
        }
        return ret;
    }

    public boolean deleteNotice(){

        return true;
    }


}
