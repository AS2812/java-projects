import java.util.*;

class MenuManager {
    private static Map<String, MenuItem> menu;

    public MenuManager()
    {
        menu = new HashMap<>();
    }

    public void addMenu(String name, double price, String description, String category) {
        MenuItem newItem = new MenuItem(name, price, description, category);
        menu.put(name, newItem);
    }

    public void updateMenu(String name, double price, String description, String category) {
        if (menu.containsKey(name)) {
            MenuItem item = menu.get(name);
            item.setPrice(price);
            item.setDescription(description);
            item.setCategory(category);
        } else {
            System.out.println("Item not found in the menu.");
        }
    }

    public void removeItem(String name) {
        if (menu.containsKey(name)) {
            menu.remove(name);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found in the menu.");
        }
    }

    public static MenuItem searchName(String name)
    {
        return menu.get(name);
    }

    public List<MenuItem> searchCategory(String category) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu.values()) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void displayMenu() {
        if (menu.isEmpty()) {
            System.out.println("Menu is empty.");
        } else {
            System.out.println("Menu:");
            for (MenuItem item : menu.values()) {
                System.out.println(item);
            }
        }
    }
     public static MenuItem getMenuItem(int menuItemNumber) {
        // Adjust index to match List index (subtract 1 because menu items are 1-indexed)
        int index = menuItemNumber - 1;
        if (index >= 0 && index < menu.size()) {
            return menu.get(index);
        } else {
            return null; // Return null if the index is out of bounds
        }
    }
}

