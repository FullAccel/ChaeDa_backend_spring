package Chaeda_spring.cloud_service_agents.file.fileController;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {

//    @PostMapping("/presigned-url")
//    @Operation(summary = "한 장의 이미지를 업로드할 presigned-url 요청")
//    public ResponseEntity<ImageResponse> createPresignedUrl(
//            @PathVariable Long memberId,
//            @Valid @RequestBody UploadImageRequest request,
//            @AuthenticationPrincipal Member member) {
//
//
////        return ResponseEntity.ok(imageService.createFileUploadUrl(memberId, request));
//    }
}
