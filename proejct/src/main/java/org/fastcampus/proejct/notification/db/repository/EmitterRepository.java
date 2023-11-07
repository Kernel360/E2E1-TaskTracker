package org.fastcampus.proejct.notification.db.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class EmitterRepository {
    // 유저ID를 키로 SseEmitter를 해시맵에 저장할 수 있도록 구현했다.
    private final Map<String, SseEmitter> emitterMap = new HashMap<>();

    public SseEmitter save(Long userId, SseEmitter sseEmitter) {
        emitterMap.put(getKey(userId), sseEmitter);
        log.info("Saved SseEmitter for {}", userId);
        return sseEmitter;
    }

    public Optional<SseEmitter> get(Long userId) {
        log.info("Got SseEmitter for {}", userId);
        return Optional.ofNullable(emitterMap.get(getKey(userId)));
    }

    public void delete(Long userId) {
        emitterMap.remove(getKey(userId));
        log.info("Deleted SseEmitter for {}", userId);
    }

    private String getKey(Long userId) {
        return "Emitter:UID:" + userId;
    }
}
