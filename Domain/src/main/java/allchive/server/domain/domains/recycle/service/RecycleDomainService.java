package allchive.server.domain.domains.recycle.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.recycle.adaptor.RecycleAdaptor;
import allchive.server.domain.domains.recycle.domain.Recycle;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class RecycleDomainService {
    private final RecycleAdaptor recycleAdaptor;

    public void save(Recycle recycle) {
        recycleAdaptor.save(recycle);
    }
}
