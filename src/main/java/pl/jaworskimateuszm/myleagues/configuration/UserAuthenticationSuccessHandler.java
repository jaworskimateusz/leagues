package pl.jaworskimateuszm.myleagues.configuration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.jaworskimateuszm.myleagues.mapper.PlayerMapper;
import pl.jaworskimateuszm.myleagues.mapper.UserMapper;
import pl.jaworskimateuszm.myleagues.model.User;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private UserMapper userMapper;
    public UserAuthenticationSuccessHandler(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        User user = userMapper.findByUsername(authentication.getName());
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/home");
    }

}
