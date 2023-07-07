package allchive.server.domain.domains.recycle.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.repository.RecycleRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class RecycleDomainService {
    private final RecycleRepository recycleRepository;

    public void save(Recycle recycle) {
        recycleRepository.save(recycle);
    }
}
