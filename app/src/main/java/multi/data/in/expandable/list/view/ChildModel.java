package multi.data.in.expandable.list.view;

public class ChildModel {

    private long id;
    private String childName;
    private String childDescription;
    private int childImage;

    public ChildModel() {
    }

    public ChildModel(long id, String childName, String childDescription, int childImage) {
        this.id = id;
        this.childName = childName;
        this.childDescription = childDescription;
        this.childImage = childImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildDescription() {
        return childDescription;
    }

    public void setChildDescription(String childDescription) {
        this.childDescription = childDescription;
    }

    public int getChildImage() {
        return childImage;
    }

    public void setChildImage(int childImage) {
        this.childImage = childImage;
    }
}
