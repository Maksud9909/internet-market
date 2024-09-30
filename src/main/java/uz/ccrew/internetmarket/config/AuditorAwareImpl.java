package uz.ccrew.internetmarket.config;

import uz.ccrew.internetmarket.entity.User;
import uz.ccrew.internetmarket.util.AuthUtil;

import org.springframework.stereotype.Component;
import org.springframework.data.domain.AuditorAware;

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