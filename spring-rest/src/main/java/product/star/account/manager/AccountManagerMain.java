package product.star.account.manager;

import com.product.star.homework.ContactConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {
        "product.star.account.manager",
        "com.product.star.homework"
})
@Import(ContactConfiguration.class)
public class AccountManagerMain {

    public static void main(String[] args) {
        SpringApplication.run(AccountManagerMain.class, args);
    }
}