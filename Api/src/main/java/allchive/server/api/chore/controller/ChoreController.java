package allchive.server.api.chore.controller;

import allchive.server.api.archiving.model.dto.request.CreateArchivingRequest;
import allchive.server.api.archiving.model.dto.request.UpdateArchivingRequest;
import allchive.server.core.error.exception.InternalServerError;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etc")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "etc. [chore]")
public class ChoreController {
    @Operation(summary = "error example")
    @PostMapping(value = "error/{test}")
    public void errorExample(@RequestBody UpdateArchivingRequest updateArchivingRequest,
                             @RequestParam("category") Category category,
                             @PathVariable("test") Long test) {
        throw new RuntimeException();
    }
}
