import com.example.connect5_project.login.bean.LoginBeanRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoginBeanRequest {
    @Test
    public void settingEmail() {
        LoginBeanRequest beanRequest = new LoginBeanRequest();
        boolean isSetted = beanRequest.setEmail("federico@gmail.com");
        assertTrue(isSetted);
    }
}
