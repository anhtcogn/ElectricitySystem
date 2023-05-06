import com.electricitysystem.ElectricitySystemApplication;
import com.electricitysystem.controller.AccountControllerTest;
import com.electricitysystem.controller.ElectricBoardControllerTest;
import org.junit.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Suite
@SelectPackages({"com.electricitysystem.controller","com.electricitysystem.repository"})
//@SelectClasses({AccountControllerTest.class, ElectricBoardControllerTest.class})
public class ElectricSystemApplicationTests {
    @Test
    public void context(){

    }
}
