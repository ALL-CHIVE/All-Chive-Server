package allchive.server.api.chore.controller;


import allchive.server.api.archiving.model.dto.request.UpdateArchivingRequest;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chore")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "etc. [chore]")
public class ChoreController {
    @Operation(hidden = true)
    @PostMapping(value = "error/{test}")
    public void errorExample(
            @RequestBody UpdateArchivingRequest updateArchivingRequest,
            @RequestParam("category") Category category,
            @PathVariable("test") Long test) {
        throw new RuntimeException();
    }

    @Operation(hidden = true)
    @GetMapping(value = "health")
    public void healthCheck() {
    }
}
