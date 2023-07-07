package allchive.server.api.user.model.mapper;


import allchive.server.api.user.model.dto.response.GetUserProfileResponse;
import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.user.domain.User;
import java.util.List;

@Mapper
public class UserMapper {
    public GetUserProfileResponse toGetUserProfileResponse(List<Category> categoryList, User user) {
        int linkCount = 0, imgCount = 0, publicCategoryCount = 0;
        for (Category category : categoryList) {
            linkCount += category.getLinkCnt();
            imgCount += category.getImgCnt();
            publicCategoryCount += category.getPublicStatus() ? 1 : 0;
        }
        return GetUserProfileResponse.of(user, linkCount, imgCount, publicCategoryCount);
    }
}
