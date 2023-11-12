package allchive.server.domain.domains.quitReason.service;

import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.quitReason.adaptor.QuitReasonAdaptor;
import allchive.server.domain.domains.quitReason.domain.QuitReason;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class QuitReasonDomainService {
    private final QuitReasonAdaptor quitReasonAdaptor;

    public void save(QuitReason quitReason) {
        quitReasonAdaptor.save(quitReason);
    }
}
