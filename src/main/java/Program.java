import Models.Category;
import Services.CategoryService;
import Services.ShoppingListManagementService;

public class Program {
    public static void main(String args[]) {
        CategoryService categoryService = new CategoryService("src/main/java/products.txt");
        ShoppingListManagementService shoppingListManagementService = new ShoppingListManagementService();

        while(true) {
            shoppingListManagementService.displayMenu();

        }
    }
}
