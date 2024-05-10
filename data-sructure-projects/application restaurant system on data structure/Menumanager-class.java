import java.util.*;
class MenuManager {
    private Map<String, MenuItem> menu;

    public MenuManager()
    {
        menu = new HashMap<>();
    }


    public void addMenu(String name, double price, String description, String category)
    {
        MenuItem newItem = new MenuItem(name, price, description, category);
        menu.put(name, newItem);
    }

    public void updateMenu(String name, double price, String description, String category) {
        if (menu.containsKey(name))
        {
            MenuItem item = menu.get(name);
            item.setPrice(price);
            item.setDescription(description);
            item.setCategory(category);
        }
        else
        {
            System.out.println("Item not found in the menu.");
        }
    }


    public void removeItem(String name)
    {
        if (menu.containsKey(name))
        {
            menu.remove(name);
            System.out.println("Item removed successfully.");
        }
        else
        {
            System.out.println("Item not found in the menu.");
        }
    }


    public MenuItem searchName(String name)
    {
        return menu.get(name);
    }


    public List<MenuItem> searchCategory(String category)
    {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu.values()) {
            if (item.getCategory().equalsIgnoreCase(category))
            {
                result.add(item);
            }
        }
        return result;
    }


    public void displayMenu()
    {
        for (MenuItem item : menu.values())
        {
            System.out.println(item);
        }
    }

    }

