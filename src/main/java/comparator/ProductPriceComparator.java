package comparator;

import com.li.tmall.pojo.Product;

import java.util.Comparator;

/**
 * Author: 98050
 * Time: 2018-09-27 23:22
 * Feature:
 */
public class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        double result = o1.getPromotePrice() - o2.getPromotePrice();

        if (result > 0){
            return (int)(result+1);
        }else if (result == 0){
            return 0;
        }else {
            return (int) (result - 1);
        }

    }
}
