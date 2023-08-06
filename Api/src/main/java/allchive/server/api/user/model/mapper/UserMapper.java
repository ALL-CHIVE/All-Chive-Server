package allchive.server.api.user.model.mapper;

import static allchive.server.core.consts.AllchiveConst.PLUS_ONE;
import static allchive.server.core.consts.AllchiveConst.ZERO;

import allchive.server.api.user.model.dto.response.GetUserProfileResponse;
import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.user.domain.User;
import java.util.List;

@Mapper
public class UserMapper {
    public GetUserProfileResponse toGetUserProfileResponse(
            List<Archiving> archivingList, User user) {
        int linkCount = 0, imgCount = 0, publicArchivingCount = 0;
        for (Archiving archiving : archivingList) {
            linkCount += archiving.getLinkCnt();
            imgCount += archiving.getImgCnt();
            publicArchivingCount += archiving.getPublicStatus() ? PLUS_ONE : ZERO;
        }
        return GetUserProfileResponse.of(
                user, linkCount, imgCount, publicArchivingCount, archivingList.size());
    }
}
