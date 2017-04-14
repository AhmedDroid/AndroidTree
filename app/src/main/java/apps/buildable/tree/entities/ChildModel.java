package apps.buildable.tree.entities;

/**
 * Created by SamerGigaByte on 4/14/2017.
 */

public class ChildModel {
    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChildModel(int imageRes, String name) {
        this.imageRes = imageRes;
        this.name = name;
    }

    private int imageRes;
    private String name;
}
