package allchive.server.api.block.controller;


import allchive.server.api.block.model.dto.request.BlockRequest;
import allchive.server.api.block.model.dto.response.BlockResponse;
import allchive.server.api.block.model.dto.response.BlockUsersResponse;
import allchive.server.api.block.service.CreateBlockUseCase;
import allchive.server.api.block.service.DeleteBlockUseCase;
import allchive.server.api.block.service.GetBlockUseCase;
import allchive.server.api.config.security.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blocks")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "8. [block]")
public class BlockController {
    private final CreateBlockUseCase createBlockUseCase;
    private final DeleteBlockUseCase deleteBlockUseCase;
    private final GetBlockUseCase getBlockUseCase;

    @Operation(summary = "유저를 차단합니다.")
    @PostMapping()
    public BlockResponse createBlock(@RequestBody BlockRequest blockRequest) {
        return createBlockUseCase.execute(blockRequest);
    }

    @Operation(summary = "유저 차단을 해제합니다.")
    @DeleteMapping()
    public BlockResponse deleteBlock(@RequestBody BlockRequest blockRequest) {
        Long userId = SecurityUtil.getCurrentUserId();
        return deleteBlockUseCase.execute(blockRequest, userId, blockRequest.getUserId());
    }

    @Operation(summary = "차단한 유저 정보를 가져옵니다.")
    @GetMapping()
    public BlockUsersResponse getBlock() {
        return getBlockUseCase.execute();
    }
}
