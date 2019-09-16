package mate.academy.internetshop;

public class IdGenerator {

    private static Long itemId = 0L;
    private static Long bucketId = 0L;
    private static Long orderId = 0L;
    private static Long userId = 0L;

    public static Long getUserId() {
        return userId++;
    }

    public static Long getItemId() {
        return itemId++;
    }

    public static Long getOrderId() {
        return orderId++;
    }

    public static Long getBucketId() {
        return bucketId++;
    }
}
