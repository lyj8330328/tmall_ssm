package interceptor;

import com.li.tmall.pojo.Category;
import com.li.tmall.pojo.OrderItem;
import com.li.tmall.pojo.User;
import com.li.tmall.service.CategoryService;
import com.li.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-29 21:04
 * Feature:其他功能的拦截器
 */
public class OtherInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        HttpSession session = request.getSession();
        /**
         * 获取分类集合信息，放在搜索栏下面
         */
        List<Category> categories = categoryService.list();
        session.setAttribute("cs",categories);

        /**
         * 获取当前的contextPath:tmall_ssm,用与放在左上角那个变形金刚，点击之后才能够跳转到首页，否则点击之后也仅仅停留在当前页面
         */
        String contextPath = session.getServletContext().getContextPath();
        session.setAttribute("contextPath",contextPath);

        /**
         * 获取购物车中一共有多少数量
         */
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<OrderItem> orderItems = orderItemService.listByUser(user.getId());
            int cartTotalItemNumber = 0;
            for (OrderItem orderItem : orderItems) {
                cartTotalItemNumber += orderItem.getNumber();
            }
            session.setAttribute("cartTotalItemNumber", cartTotalItemNumber);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
