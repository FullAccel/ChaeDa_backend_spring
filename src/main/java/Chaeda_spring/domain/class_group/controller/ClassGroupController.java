package Chaeda_spring.domain.class_group.controller;

import Chaeda_spring.domain.class_group.dto.ClassGroupRequest;
import Chaeda_spring.domain.class_group.dto.ClassGroupResponse;
import Chaeda_spring.domain.class_group.service.ClassGroupService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/class_group")
public class ClassGroupController {

    private final ClassGroupService classGroupService;

    @GetMapping("/{memberId}")
    @Operation(summary = "(선생님용) 자신의 클래스 목록 조회")
    public ResponseEntity<List<ClassGroupResponse>> getClassGroupList(@PathVariable Long memberId) {
        return ResponseEntity.ok().body(classGroupService.getClassGroupList(memberId));
    }

    @PostMapping("/{memberId}")
    @Operation(summary = "(선생님용) 클래스 생성", description = "이미지를 먼저 S3에 업로드한 후 해당 정보를 담아 보내주세요")
    public ResponseEntity<Long> createClassGroup(
            @PathVariable Long memberId,
            @Valid @RequestBody ClassGroupRequest classGroupRequest
    ) {
        return ResponseEntity.ok().body(classGroupService.createClassGroup(memberId, classGroupRequest));
    }
}
