package allchive.server.api.archiving.model.mapper;


import allchive.server.api.archiving.model.dto.request.CreateArchivingRequest;
import allchive.server.api.archiving.model.dto.response.ArchivingTitleResponse;
import allchive.server.api.archiving.model.vo.TitleContentCntVo;
import allchive.server.api.common.util.UrlUtil;
import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.archiving.domain.Archiving;
import java.util.List;

@Mapper
public class ArchivingMapper {
    public Archiving toEntity(CreateArchivingRequest request, Long userId) {
        return Archiving.of(
                userId,
                request.getTitle(),
                UrlUtil.convertUrlToKey(request.getImageUrl()),
                request.isPublicStatus(),
                request.getSubject());
    }

    public ArchivingTitleResponse toArchivingTitleResponse(List<Archiving> archivings) {
        ArchivingTitleResponse response = ArchivingTitleResponse.init();
        archivings.forEach(
                archiving -> {
                    switch (archiving.getSubject()) {
                        case FOOD -> response.addFood(TitleContentCntVo.from(archiving));
                        case LIFE -> response.addLife(TitleContentCntVo.from(archiving));
                        case HOME_LIVING -> response.addHomeLiving(
                                TitleContentCntVo.from(archiving));
                        case SHOPPING -> response.addShopping(TitleContentCntVo.from(archiving));
                        case SPORT -> response.addSport(TitleContentCntVo.from(archiving));
                        case SELF_IMPROVEMENT -> response.addSelfImprovement(
                                TitleContentCntVo.from(archiving));
                        case TECH -> response.addTech(TitleContentCntVo.from(archiving));
                        case DESIGN -> response.addDesign(TitleContentCntVo.from(archiving));
                        case TREND -> response.addTrend(TitleContentCntVo.from(archiving));
                    }
                });
        return response;
    }
}
