package Chaeda_spring.deprecated.class_group.controller;

import Chaeda_spring.deprecated.class_group.dto.ClassGroupRequest;
import Chaeda_spring.deprecated.class_group.dto.ClassGroupResponse;
import Chaeda_spring.deprecated.class_group.dto.ClassGroupSummaryResponse;
import Chaeda_spring.deprecated.class_group.service.ClassGroupService;
import Chaeda_spring.domain.image.dto.UploadImageCompleteRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Deprecated
@RequestMapping("/class_group")
public class ClassGroupController {

    private final ClassGroupService classGroupService;

    @GetMapping("/classList/{memberId}")
    @Operation(summary = "(선생님용) 자신의 클래스 목록 조회")
    public ResponseEntity<List<ClassGroupSummaryResponse>> getClassGroupList(@PathVariable Long memberId) {
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

    @GetMapping("/classInfo/{classGroupId}")
    @Operation(summary = "클래스 상세 정보 조회", description = "클래스 정보와 소속 학생들의 요약정보가 담겨있습니다")
    public ResponseEntity<ClassGroupResponse> getClassGroupInfo(
            @PathVariable Long classGroupId
    ) {
        return ResponseEntity.ok(classGroupService.getClassGroup(classGroupId));
    }

    @PutMapping("/{classGroupId}/studentList")
    @Operation(summary = "(선생님용) 클래스에 학생들 추가")
    public ResponseEntity<Void> addStudentListInClass(
            @PathVariable Long classGroupId,
            @Schema(description = "학생들 Id List입니다")
            @RequestBody
            List<Long> memberIdList) {
        classGroupService.addStudentListInClass(classGroupId, memberIdList);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/{classGroupId}/studentList")
    @Operation(summary = "(선생님용) 클래스에 학생들 삭제")
    public ResponseEntity<Void> deleteStudentListInClass(
            @PathVariable Long classGroupId,
            @Schema(description = "학생들 Id List입니다")
            @RequestBody
            List<Long> memberIdList) {
        classGroupService.deleteStudentListInClass(classGroupId, memberIdList);
        return ResponseEntity.ok().body(null);
    }

    @Deprecated
    @PutMapping("profile-image/{classGroupId}")
    @Operation(summary = "(선생님용) 클래스 프로필 이미지 수정")
    public ResponseEntity<Void> updateClassProfileImage(
            @PathVariable Long classGroupId,
            @RequestBody UploadImageCompleteRequest request
    ) {
        classGroupService.updateClassGroupProfileImage(classGroupId, request);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("profile-image/{classGroupId}")
    @Operation(summary = "(선생님용) 클래스 프로필 이미지 삭제")
    public ResponseEntity<Void> deleteClassProfileImage(
            @PathVariable Long classGroupId
    ) {
        classGroupService.deleteClassGroupProfileImage(classGroupId);
        return ResponseEntity.ok().body(null);
    }

}
