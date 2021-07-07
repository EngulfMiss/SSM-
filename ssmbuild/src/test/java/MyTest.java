import com.engulf.pojo.Books;
import com.engulf.service.BookService;
import com.engulf.service.BookServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = ac.getBean("BookServiceImpl", BookService.class);
        List<Books> books = bookService.selectAllBooks();
        for (Books book : books) {
            System.out.println(book);
        }
    }
}
