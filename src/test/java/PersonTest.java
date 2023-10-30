import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.assertEquals;

public class PersonTest {

    public void currentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

    @BeforeClass
    void beforeClass() {
        System.out.println("Before class");
        currentTime();
    }

    @AfterClass
    void afterClass() {
        System.out.println("After class");
        currentTime();
    }

    @Test(dataProvider = "ageList")
    void testTeenagerAge(int age, boolean expected) {
        boolean result = Person.isTeenager(age);
        System.out.println("Возраст - " + age + ", фактич. результат - " + result + ", ожидаемый результат - " + expected);
        if (result != expected) {
            System.out.println("Указан возраст (" + age + "), несоответствующий возрасту тинейджера (от 13 до 19)");
        }
        assertEquals(result, expected);
    }

    @DataProvider(name = "ageList")
    Object[][] dataProvider1() {
        return new Object[][]{
                {-1, false},
                {0, false},
                {7, false},
                {12, false},
                {13, true},
                {14, true},
                {16, true},
                {18, true},
                {19, true},
                {20, false},
                {30, false},
                {99, false},
        };
    }
}
