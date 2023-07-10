package allchive.server.api.user.model.mapper;


import allchive.server.api.user.model.dto.response.GetUserProfileResponse;
import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.user.domain.User;
import java.util.List;

@Mapper
public class UserMapper {
    public GetUserProfileResponse toGetUserProfileResponse(List<Archiving> archivingList, User user) {
        int linkCount = 0, imgCount = 0, publicArchivingCount = 0;
        for (Archiving archiving : archivingList) {
            linkCount += archiving.getLinkCnt();
            imgCount += archiving.getImgCnt();
            publicArchivingCount += archiving.getPublicStatus() ? 1 : 0;
        }
        return GetUserProfileResponse.of(user, linkCount, imgCount, publicArchivingCount);
    }
}
