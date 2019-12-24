/**
 * 统计类：内存占用、耗时
 * 测内存占用如果被统计的代码块进行gc，会对统计结果造成影响
 * 暂未测试
 */
public class CostUtil {

    private static ThreadLocal<Long> memCostBegin;
    private static ThreadLocal<Long> timeCostBegin;
    private static ThreadLocal<Runtime> runtime;

    public static void memCostStart(){
        Runtime r = Runtime.getRuntime();
        runtime.set(r);
        r.gc();
        memCostBegin.set(r.freeMemory());
    }

    public static void memCostEnd(){
        Runtime r = runtime.get();
        System.out.println("cost memory:" + (memCostBegin.get()-r.freeMemory()) + "B");
    }

    public static void timeCostStart(){
        long start = System.currentTimeMillis();
        timeCostBegin.set(start);
    }

    public static void timeCostEnd(){
        System.out.println("cost time:" + (System.currentTimeMillis()-timeCostBegin.get()) + "ms");
    }

}
