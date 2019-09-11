package multi.data.in.expandable.list.view;

import java.util.ArrayList;

public class GroupModel {

    private long id;
    private String groupName;
    private String groupDescription;
    private ArrayList<ChildModel> childArrayList = new ArrayList<ChildModel>();

    public GroupModel() {
    }

    public GroupModel(long id, String groupName, String groupDescription) {
        this.id = id;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public ArrayList<ChildModel> getChildArrayList() {
        return childArrayList;
    }

    public void setChildArrayList(ArrayList<ChildModel> childArrayList) {
        this.childArrayList = childArrayList;
    }
}
