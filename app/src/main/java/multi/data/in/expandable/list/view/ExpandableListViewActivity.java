package multi.data.in.expandable.list.view;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

public class ExpandableListViewActivity extends AppCompatActivity {

    private ArrayList<GroupModel> groupModelArrayList;
    private ArrayList<NullPositionModel> nullPositionModelArrayList;
    private HashMap<String, GroupModel> groupModelHashMap;

    private ExpandableListViewAdapter expandableListViewAdapter;
    private ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        // initializing the views
        initViews();

        // initializing the listeners
        initListeners();

        // initializing the objects
        initObjects();

        // preparing list data
        initListData();

        //expand all the Groups
        expandAll();
    }

    /**
     * method to initialize the views
     */
    private void initViews() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
    }

    /**
     * method to initialize the listeners
     */
    private void initListeners() {

        // ExpandableListView on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                GroupModel groupModel = groupModelArrayList.get(groupPosition);
                ChildModel  childModel =  groupModel.getChildArrayList().get(childPosition);
                Toast.makeText(getBaseContext(), " Clicked on :: "+groupModel.getGroupName()+"/"+((ChildModel) childModel).getChildName(), Toast.LENGTH_LONG).show();
                return false;
            }
        });

        // ExpandableListView on group click listener
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                GroupModel groupModel = groupModelArrayList.get(groupPosition);
                Toast.makeText(getBaseContext(), " Header is :: "+groupModel.getGroupName(),Toast.LENGTH_LONG).show();
                return false;
            }
        });

        // ExpandableListView Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                GroupModel groupModel = groupModelArrayList.get(groupPosition);
                Toast.makeText(getApplicationContext(), groupModel.getGroupName() + " Expanded", Toast.LENGTH_SHORT).show();
            }
        });

        // ExpandableListView Group collapsed listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                GroupModel groupModel = groupModelArrayList.get(groupPosition);
                Toast.makeText(getApplicationContext(), groupModel.getGroupName() + " Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * method to initialize the objects
     */
    private void initObjects() {
        groupModelArrayList = new ArrayList<GroupModel>();
        groupModelHashMap = new HashMap<String, GroupModel>();
        nullPositionModelArrayList= new ArrayList<NullPositionModel>();

        // initializing the adapter object
        expandableListViewAdapter = new ExpandableListViewAdapter(ExpandableListViewActivity.this,groupModelArrayList,nullPositionModelArrayList);

        // setting list adapter
        expandableListView.setAdapter(expandableListViewAdapter);
    }

    /*
     * Preparing the list data
     *
     */
    private void initListData()
    {
        prepareListData(1, "Group 1 Name", "Group 1 Description",7, "Child 1 Name", "Group 1 Description");
        prepareListData(2, "Group 2 Name", "Group 2 Description",5, "null", "Group 2 Description");
        prepareListData(3, "Group 3 Name", "Group 3 Description",3, "Child 3 Name", "Group 3 Description");
        prepareListData(4, "Group 4 Name", "Group 4 Description",2, "Child 4 Name", "Group 4 Description");

        ArrayList<Integer> count = new ArrayList<Integer>();
        for (int i = 0; i < groupModelArrayList.size(); i++)
        {
            ArrayList<ChildModel> childModelArrayList = groupModelArrayList.get(i).getChildArrayList();
            System.out.println("********************GROUP "+groupModelArrayList.size()+"****************"+groupModelArrayList.get(i).getGroupName());

            for (int j = 0; j < childModelArrayList.size(); j++)
            {
                System.out.println("********************CHILD "+childModelArrayList.size()+"****************"+childModelArrayList.get(j).getChildName());
                if(childModelArrayList.get(j).getChildName().equals("null"))
                {
                    count.add(j);
                    System.out.println("********************NULL****************"+childModelArrayList.size());
                    if (childModelArrayList.size()==count.size())
                    {
                        nullPositionModelArrayList.add(new NullPositionModel(i));
                        System.out.println("##############"+nullPositionModelArrayList.get(0).getNullPosition());
                    }
                }
            }
        }

        // notify the adapter
        expandableListViewAdapter.notifyDataSetChanged();
        expandableListViewAdapter.notifyDataSetInvalidated();
    }

    /*
     * Expand all groups
     *
     */
    private void expandAll() {
        int count = expandableListViewAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            expandableListView.expandGroup(i);
        }
    }

    /*
     * Collapse all groups
     *
     */
    private void collapseAll() {
        int count =expandableListViewAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            expandableListView.collapseGroup(i);
        }
    }

    private void prepareListData(long id, String groupName, String groupDescription,int numberOfTimeCreateData, String childName, String childDescription)
    {
        GroupModel groupModel = createGroup(id, groupName, groupDescription);
        groupModel.setChildArrayList(createItems(numberOfTimeCreateData,childName, childDescription));
        groupModelArrayList.add(groupModel);
    }

    private GroupModel createGroup(long id, String name, String description)
    {
        return new GroupModel(id, name, description);
    }

    private ArrayList<ChildModel> createItems(int numberOfTimeCreateData, String name, String description)
    {
        ArrayList<ChildModel> childModelArrayList= new ArrayList<ChildModel>();

        for (int i=0; i < numberOfTimeCreateData; i++)
        {
            ChildModel item = new ChildModel(i, name, description,R.drawable.pic_2);
            childModelArrayList.add(item);
        }
        return childModelArrayList;
    }
}