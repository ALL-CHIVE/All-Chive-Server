package allchive.server.domain.domains.user.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.exception.exceptions.UserNotFoundException;
import allchive.server.domain.domains.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor {
    private final UserRepository userRepository;

    public Boolean exist(OauthInfo oauthInfo) {
        return userRepository.existsByOauthInfo(oauthInfo);
    }

    public User findByOauthInfo(OauthInfo oauthInfo) {
        return userRepository
                .findByOauthInfo(oauthInfo)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public Boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public List<User> findAllByIdIn(List<Long> userIds) {
        return userRepository.findAllByIdIn(userIds);
    }

    public Boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }
}
