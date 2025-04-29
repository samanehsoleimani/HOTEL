package manager;

import common.Constants;
import common.Utils;
import java.util.ArrayList;
import java.util.List;

public class TrainingSystem {
    private List<String[]> registrations = new ArrayList<>();

    public String[][] getCoursesByCategory(int categoryIndex) {
        return Constants.COURSES[categoryIndex];
    }

    public String[] getCategories() {
        return Constants.CATEGORIES;
    }

    public void registerCourse(String fullName, String phone, int courseId) {
        int group = (courseId-1)/4;
        int index = (courseId-1)%4;
        registrations.add(new String[]{
                fullName,
                phone,
                Constants.COURSES[group][index][0],
                Constants.COURSES[group][index][1]
        });
    }

    public String calculateGroupDiscount(int count, int courseId) {
        int group = (courseId-1)/4;
        int index = (courseId-1)%4;
        int price = Integer.parseInt(Constants.COURSES[group][index][1]);

        int discount = (count >= 5) ? 15 : (count >= 3) ? 10 : 0;
        int total = price * count;
        int finalPrice = total - (total * discount / 100);

        return String.format("""
            دوره: %s
            قیمت پایه: %s
            تعداد: %d
            تخفیف: %d%%
            مبلغ کل: %s
            """,
                Constants.COURSES[group][index][0],
                Utils.formatPrice(price),
                count,
                discount,
                Utils.formatPrice(finalPrice)
        );
    }
}