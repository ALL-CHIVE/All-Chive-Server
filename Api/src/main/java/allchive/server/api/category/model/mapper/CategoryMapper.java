package allchive.server.api.category.model.mapper;


import allchive.server.api.category.model.dto.request.CreateCategoryRequest;
import allchive.server.api.category.model.dto.response.CategoryTitleResponse;
import allchive.server.api.category.model.vo.TitleContentCntVo;
import allchive.server.api.common.util.UrlUtil;
import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.category.domain.Category;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Mapper
public class CategoryMapper {
    public Category toEntity(CreateCategoryRequest request, Long userId) {
        return Category.of(
                userId,
                request.getTitle(),
                UrlUtil.convertUrlToKey(request.getImageUrl()),
                request.isPublicStatus(),
                request.getSubject());
    }

    public CategoryTitleResponse toCategoryTitleResponse(List<Category> categories) {
        CategoryTitleResponse response = CategoryTitleResponse.init();
        categories.forEach(
                category -> {
                    switch (category.getSubject()) {
                        case FOOD -> response.addFood(TitleContentCntVo.from(category));
                        case LIFE -> response.addLife(TitleContentCntVo.from(category));
                        case HOME_LIVING -> response.addHomeLiving(
                                TitleContentCntVo.from(category));
                        case SHOPPING -> response.addShopping(TitleContentCntVo.from(category));
                        case SPORT -> response.addSport(TitleContentCntVo.from(category));
                        case SELF_IMPROVEMENT -> response.addSelfImprovement(
                                TitleContentCntVo.from(category));
                        case TECH -> response.addTech(TitleContentCntVo.from(category));
                        case DESIGN -> response.addDesign(TitleContentCntVo.from(category));
                        case TREND -> response.addTrend(TitleContentCntVo.from(category));
                    }
                });
        return response;
    }
}
