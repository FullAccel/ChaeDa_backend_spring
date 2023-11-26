package Chaeda_spring.domain.class_group.controller;

import Chaeda_spring.domain.class_group.dto.ClassGroupResponseDto;
import Chaeda_spring.domain.class_group.service.ClassGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/class_group")
public class ClassGroupController {

    private final ClassGroupService classGroupService;

    @GetMapping("/{memberId}")
    public ResponseEntity<List<ClassGroupResponseDto>> getClassGroupList(@PathVariable Long memberId) {
        return ResponseEntity.ok().body(classGroupService.getClassGroupList(memberId));
    }
}
