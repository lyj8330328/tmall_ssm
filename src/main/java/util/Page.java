package util;

/**
 * @Author: 98050
 * Time: 2018-09-17 19:27
 * Feature:
 */
public class Page {
    /**
     *  每页从第几个开始
     */
    private int start;
    /**
     * 每页显示的个数
     */
    private int count;
    /**
     * 总数
     */
    private int total;
    /**
     * 参数
     */
    private String param;

    /**
     * 默认每页显示5条
     */
    private static final int defaultCount = 5;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Page(){
        count = defaultCount;
    }

    public Page(int start,int count){
        /**
         * 先调用无参构造方法
         */
        this();
        this.start = start;
        this.count = count;
    }

    public boolean isHasPreviouse(){
        if(start == 0) {
            return false;
        }
        return true;
    }

    public boolean isHasNext(){
        if (start == getLast()){
            return false;
        }else {
            return true;
        }
    }

    public int getTotalPage(){
        int totalPage;

        if (total % count == 0){
            totalPage = total / count;
        }else {
            totalPage = total / count +1;
        }
        if (totalPage == 0){
            totalPage = 1;
        }
        return totalPage;
    }

    public int getLast(){
        int last;

        if (total % count == 0){
            last = total - count;
        }
        else {
            last = total - total % count;
        }
        last = last < 0 ? 0 : last;
        return last;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                ", param='" + param + '\'' +
                '}';
    }
}
