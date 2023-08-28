package allchive.server.domain.domains.content.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.content.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

@DomainService
@RequiredArgsConstructor
public class TagAsyncDomainService {
    private final TagDomainService tagDomainService;

    @Async(value = "tagTaskExecutor")
    public void updateUsedAt(Tag tag) {
        tagDomainService.updateUsedAt(tag);
    }
}
