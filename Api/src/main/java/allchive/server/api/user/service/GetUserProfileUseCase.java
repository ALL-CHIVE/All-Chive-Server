package allchive.server.api.user.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.user.model.dto.response.GetUserProfileResponse;
import allchive.server.api.user.model.mapper.UserMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.validator.UserValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetUserProfileUseCase {
    private final CategoryAdaptor categoryAdaptor;
    private final UserAdaptor userAdaptor;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    @Transactional(readOnly = true)
    public GetUserProfileResponse execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        userValidator.validateUserStatusNormal(userId);
        User user = userAdaptor.queryUserById(userId);
        List<Category> categoryList = categoryAdaptor.findAllByUserId(userId);
        return userMapper.toGetUserProfileResponse(categoryList, user);
    }
}
