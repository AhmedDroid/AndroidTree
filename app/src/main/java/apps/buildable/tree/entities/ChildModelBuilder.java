package apps.buildable.tree.entities;

public class ChildModelBuilder {
    private int mImageRes;
    private String mName;

    public ChildModelBuilder setImageRes(int imageRes) {
        mImageRes = imageRes;
        return this;
    }

    public ChildModelBuilder setName(String name) {
        mName = name;
        return this;
    }

    public ChildModel createChildModel() {
        return new ChildModel(mImageRes, mName);
    }
}