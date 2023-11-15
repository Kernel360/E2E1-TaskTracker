package org.fastcampus.proejct.notification.converter;

public class TemplateCollection {

    public enum contentTemplate{
        FRIEND_ADD("님이 친구추가를 신청하셨습니다."),
        FRIEND_REJACT("님이 친구추가를 거절하셨습니다."),
        WORK_ADD("의 할일이 추가되었습니다."),
        WORK_COMPLETE("의 할일이 완료되었습니다.");

        public String getMessage() {
            return message;
        }

        private final String message;

        contentTemplate(String message) {
            this.message = message;
        }
    }

    //공통코드 테이블로 관리하는게 아니니까 알람 발송 타입 정의 enum이 추가 되야 할 것 같아요 ~
}
