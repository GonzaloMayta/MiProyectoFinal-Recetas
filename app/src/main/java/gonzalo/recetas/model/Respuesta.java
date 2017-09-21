package gonzalo.recetas.model;
import java.util.ArrayList;


public class Respuesta {

    private boolean success;
    private int count;

    public ArrayList<Food> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Food> recipes) {
        this.recipes = recipes;
    }

    private ArrayList<Food> recipes;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}


