package uz.ccrew.internetmarket.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import uz.ccrew.internetmarket.util.AuthUtil;
import uz.ccrew.internetmarket.entity.User;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<User> {
    private final AuthUtil authUtil;

    public AuditorAwareImpl(AuthUtil authUtil) {
        this.authUtil = authUtil;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        return authUtil.takeLoggedUser();
    }
}