import com.three.action.UserAction;
import com.three.beans.*;
import com.three.utils.HibernateDao;
import com.three.utils.other.UUIDUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.weaver.ast.Or;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;

import static org.aspectj.bridge.Version.getTime;

public class TestMain {

    private static Logger logger = LogManager.getLogger(TestMain.class);
    private  SessionFactory sessionFactory;
    ApplicationContext ctx;
    HibernateDao<User> hibernateDao ;
    HibernateDao<Order> hibernateDaoOrder ;
    HibernateDao<OrderItem> hibernateDaoItem ;



    @Before
    public void doBefore(){
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
        hibernateDao = (HibernateDao<User>) ctx.getBean("hibernateDao");
        hibernateDaoOrder = (HibernateDao<Order>) ctx.getBean("hibernateDao");
        hibernateDaoItem = (HibernateDao<OrderItem>) ctx.getBean("hibernateDao");

    }



    @Test
    public void findItem(){
        String hql = "from OrderItem where item_id = 10 and b_id = 3";

//        OrderItem i = (OrderItem)
        hibernateDaoItem.findOne(hql,null);
//        System.out.println(i.getId());
    }
    @Test
    public void findOrder(){


    }



//Three
    @Test
    public void addUser(){                      // 插入用户 顺带把订单 一块插入
        String u_id = UUIDUtils.getId();
        User user = new User();
        user.setStatus(1);
        user.setUsername("zeroker");
        user.setPassword("123");

        user.setU_id(u_id);
        List<Order> orders = new ArrayList<Order>();

        for(int i=0; i<4; i++){
            Order order = new Order();
            order.setName("order_z:"+i);
            order.setOrdertime(new Timestamp(new Date().getTime()));
            order.setoId(u_id);
//            hibernateDaoOrder.insert(order);

            orders.add(order);
        }

        user.setOrders(orders);
        hibernateDao.insert(user);

        logger.info("完成！");

    }





//测试订单和订单详情是 一对多的关系           往购物车里 放东西
    //Four
    @Test
    public void addOrder(){

//        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for(int i=1; i<=5; i++){
            OrderItem orderItem = new OrderItem();
            orderItem.setItemId(13);                        //订单编号 （order的id）
            orderItem.setCount(i+10000);
            //放了 这本书（name is+0）
            orderItem.setbId(3);                                               //书的 id
//            orderItems.add(orderItem);
            hibernateDaoItem.insert(orderItem);
        }


//        hibernateDaoItem.insert(orderItems);
//        Order order = new Order();
//        order.setoId("29E41DA010274D4AA8E3C788126A2968");
//        order.setOrderItems(orderItems);



    }




//        hibernateDaoItem.insert(orderItems);
//        Order order = new Order();
//        order.setoId("29E41DA010274D4AA8E3C788126A2968");
//        order.setOrderItems(orderItems);



//    }




    //查看订单 里面的详情
    //five
    @Test
    public void findOrderInfo(){
//
//        String hql = "from Order where name = ?";
//        String []param={"order_z:0"};
//        List<Order> list  = hibernateDaoOrder.find(hql,param);
        Order order1 = hibernateDaoOrder.findById(Order.class,13);
        System.out.println(order1.getOrdertime());


        List<OrderItem> orderItems = order1.getOrderItems();
        for(OrderItem orderItem:orderItems){
            System.out.println(orderItem.getBook().getName());
            System.out.println(orderItem.getCount());
        }

    }


//    @Test
//    public void insertBookAndCategory(){
//
//        String c_id = UUIDUtils.getId();
//        BookCategory category = new BookCategory();
//        category.setStatus(1);
//        category.setCategory("java");
//
//        category.setC_id(c_id);
//
//        List<Book> bookList = new ArrayList<Book>();
//        for(int i=0; i<5; i++){
//            Book book = new Book();
//            book.setAuthor("au:"+i);
//            book.setName("name+"+i);
//            book.setB_id(c_id);
//        }
//
//        category.setBookList(bookList);
//        logger.info("完成！");
//
//    }









//
//
//
//    @Test
//    public void test_insert_user(){
//
//            System.out.println(sessionFactory);
//            HibernateDao<User> hibernateDao = (HibernateDao<User>) ctx.getBean("hibernateDao");
//            System.out.println("--------------------");
//            System.out.println(hibernateDao);
//
//
//            if(hibernateDao == null)
//                logger.info("it's null");
//            else{
//                User user = new User("2","123","36@666","110","");
//                hibernateDao.insert(user);
//            }
//
//                logger.info("yes!");
//
//    }
//
//
//    @Test
//    public void findPro(){
//
//    }
//
//
//    @Test
//    public void findBooksByPro(){
//        Book book = new Book();
//        book.setName("唐");
//        book.setAuthor("1");
//
//        String hql = "from Book ";
//        String []files = {"name","author"};
//        String []condition = {"唐","1"};
//        ArrayList<Book> list = (ArrayList<Book>) hibernateDao.findByFields(hql,files,condition);
//        for(Book book1:list)
//            System.out.println(book1.getName());
//
//    }
//
//
////    @Test
////    public void find(){
////
////        Book  book = new Book();
////        book.setName("唐");
//////        String hql = "from Book where name like '%唐%' ";
////        String hql = "from com.three.beans.Book  where name like '%唐%' or author like '%1%' ";
////        String []param={book.getName()};
////        List<Book> list  = hibernateDao.find(hql,null);
////        System.out.println("size()  :"+list.size());
////        for(Book book1:list)
////            logger.info(book1.getName());
////
////    }
//
//    @Test
//    public void test_update_user(){
//        UserAction action = null;
//        if(ctx == null)
//            System.out.println("no");
//        else
//            System.out.println("yes");
//        System.out.println(ctx );
//        action = (UserAction) ctx.getBean("userAction");
//        System.out.println(action);
//    }
//
//    @Test
//    public void testFile(){
//        File file = new File("C:\\Users\\zk\\Desktop\\Learn\\JAVA" +
//                "\\java web\\JAVA-CODE-HIGH\\Three_Work\\" +
//                "out\\artifacts\\Three_Work_Web_exploded\\file\\02.jpg");
//        logger.info("file: "+file);
//    }
//
}
