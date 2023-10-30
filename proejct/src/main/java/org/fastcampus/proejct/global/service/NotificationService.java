package org.fastcampus.proejct.global.service;

import org.fastcampus.proejct.global.domain.NotificationEntity;
import org.fastcampus.proejct.global.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * @className    : NotificationService
 * @fileName     : NotificationService.java
 * @author       : Jiyong Jung
 * @date         : 2023-10-30 :: 오후 4:29
 * @desc         :
 * ===========================================================
 * line                  AUTHOR             NOTE
 * -----------------------------------------------------------
 *   0              Jiyong Jung        최초 생성
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
     * @param notiEntity
     * @return boolean
     */
    public boolean setNotice(NotificationEntity notiEntity){
        boolean ret = false;
        try{
            notificationRepository.save(notiEntity);
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
