package allchive.server.domain.domains.archiving.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import java.util.List;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class ArchivingDomainService {
    private final ArchivingAdaptor archivingAdaptor;

    public void save(Archiving archiving) {
        archivingAdaptor.save(archiving);
    }

    public void updateArchiving(
            Archiving archiving,
            String title,
            String imageUrl,
            boolean publicStatus,
            Category category) {
        archiving.update(title, imageUrl, publicStatus, category);
        archivingAdaptor.save(archiving);
    }

    public void updateScrapCount(Long archivingId, int i) {
        Archiving archiving = archivingAdaptor.findById(archivingId);
        archiving.updateScrapCnt(i);
        archivingAdaptor.save(archiving);
    }

    public void updatePin(Long archivingId, Long userId, boolean pin) {
        Archiving archiving = archivingAdaptor.findById(archivingId);
        if (pin) {
            archiving.addPinUserId(userId);
        } else {
            archiving.deletePinUserId(userId);
        }
    }

    public void softDeleteById(Long archivingId) {
        Archiving archiving = archivingAdaptor.findById(archivingId);
        archiving.delete();
        archivingAdaptor.save(archiving);
    }

    public void restoreByIdIn(List<Long> archivingIds) {
        List<Archiving> archivings = archivingAdaptor.findAllByIdIn(archivingIds);
        archivings.forEach(Archiving::restore);
        archivingAdaptor.saveAll(archivings);
    }

    public void deleteAllById(List<Long> archivingIds) {
        archivingAdaptor.deleteAllById(archivingIds);
    }

    public void updateContentCnt(Long archivingId, ContentType contentType, int i) {
        Archiving archiving = archivingAdaptor.findById(archivingId);
        if (contentType.equals(ContentType.IMAGE)) {
            archiving.updateImgCnt(i);
        } else {
            archiving.updateLinkCnt(i);
        }
        archivingAdaptor.save(archiving);
    }
}
