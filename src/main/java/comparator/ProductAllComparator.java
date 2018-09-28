package comparator;

import com.li.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @Author: 98050
 * Time: 2018-09-27 23:16
 * Feature:
 */
public class ProductAllComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getReviewCount()*o2.getSaleCount() - o1.getReviewCount()*o1.getSaleCount();
    }
}
