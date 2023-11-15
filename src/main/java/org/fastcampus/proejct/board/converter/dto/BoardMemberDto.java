//package org.fastcampus.proejct.board.converter.dto;
//
//import org.fastcampus.proejct.auth.converter.dto.UserInfoDto;
//import org.fastcampus.proejct.board.db.model.BoardMember;
//
//import java.time.LocalDateTime;
//
///**
// * DTO for {@link BoardMember}
// */
//public record BoardMemberDto(
//        Long id,
//        BoardDto board,
//        UserInfoDto userInfo,
//        LocalDateTime createdAt,
//        String createdBy,
//        LocalDateTime modifiedAt,
//        String modifiedBy
//) {
//    public static BoardMemberDto of(
//            Long id,
//            BoardDto board,
//            UserInfoDto userInfo,
//            LocalDateTime createdAt,
//            String createdBy,
//            LocalDateTime modifiedAt,
//            String modifiedBy
//    ) {
//        return new BoardMemberDto(
//                id,
//                board,
//                userInfo,
//                createdAt,
//                createdBy,
//                modifiedAt,
//                modifiedBy
//        );
//    }
//
//    public static BoardMemberDto from(
//            BoardMember entity
//    ) {
//        return BoardMemberDto.of(
//                entity.getId(),
//                BoardDto.from(entity.getBoard()),
//                UserInfoDto.from(entity.getUserInfo()),
//                entity.getCreatedAt(),
//                entity.getCreatedBy(),
//                entity.getModifiedAt(),
//                entity.getModifiedBy()
//        );
//    }
//}