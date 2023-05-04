import com.electricitysystem.ElectricitySystemApplication;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ElectricitySystemApplication.class)
@Suite
@SelectPackages("com.electricitysystem.repository")
public class ElectricSystemApplicationTests {
}
