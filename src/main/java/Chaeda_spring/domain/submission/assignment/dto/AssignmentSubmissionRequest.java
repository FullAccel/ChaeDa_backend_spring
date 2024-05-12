package Chaeda_spring.domain.submission.assignment.dto;

import java.util.List;

public record AssignmentSubmissionRequest(

        List<WrongProblemListPerPageRequest> wrongProblemListPerPageRequests
) {
}
