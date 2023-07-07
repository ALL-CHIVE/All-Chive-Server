package allchive.server.domain.domains.recycle.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.repository.RecycleRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RecycleAdaptor {
    private final RecycleRepository recycleRepository;

    public void save(Recycle recycle) {
        recycleRepository.save(recycle);
    }
}
