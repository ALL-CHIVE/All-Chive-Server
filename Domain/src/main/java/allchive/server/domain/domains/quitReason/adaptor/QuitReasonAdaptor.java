package allchive.server.domain.domains.quitReason.adaptor;

import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.quitReason.domain.QuitReason;
import allchive.server.domain.domains.quitReason.repository.QuitReasonRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class QuitReasonAdaptor {
    private final QuitReasonRepository quitReasonRepository;

    public void save(QuitReason quitReason) {
        quitReasonRepository.save(quitReason);
    }
}
