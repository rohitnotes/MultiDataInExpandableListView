package multi.data.in.expandable.list.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter
{
    private LayoutInflater layoutInflater;
    private ArrayList<GroupModel> groupModelArrayList;
    private ArrayList<NullPositionModel> nullPositionModelArrayList;

    public ExpandableListViewAdapter(Context context, ArrayList<GroupModel> arrayList,ArrayList<NullPositionModel> nullPositionModelArrayList)
    {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupModelArrayList = arrayList;
        this.nullPositionModelArrayList=nullPositionModelArrayList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        ArrayList<ChildModel> childModelArrayList = groupModelArrayList.get(groupPosition).getChildArrayList();
        return childModelArrayList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        convertView=null;
        ChildViewHolder childViewHolder;
        ChildModel childModelPosition = groupModelArrayList.get(groupPosition).getChildArrayList().get(childPosition);

        if (childModelPosition.getChildName().trim().equals("null"))
        {
            convertView = layoutInflater.inflate(R.layout.child_item_empty, parent, false);
        }
        else
        {
            convertView = layoutInflater.inflate(R.layout.child_item, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);

            ((ChildViewHolder) childViewHolder).setData(childModelPosition);
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        ArrayList<ChildModel> childModelArrayList = groupModelArrayList.get(groupPosition).getChildArrayList();
        return childModelArrayList.size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return groupModelArrayList.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return groupModelArrayList.size();
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {

        boolean empty_layout=false;
        for (int i = 0; i < nullPositionModelArrayList.size(); i++)
        {
            if(groupPosition==nullPositionModelArrayList.get(i).getNullPosition())
            {
                empty_layout=true;
            }
        }

        convertView=null;
        GroupViewHolder groupViewHolder;
        GroupModel groupModelPosition = (GroupModel) getGroup(groupPosition);

        System.out.println(groupPosition+" Number of Child In "+groupModelPosition.getGroupName()+" "+getChildrenCount(groupPosition));
        if (empty_layout)
        {
            convertView = layoutInflater.inflate(R.layout.group_item_empty, parent, false);
            TextView groupFirstTextView =convertView.findViewById(R.id.group_first_text_view);
            TextView groupSecondTextView =convertView.findViewById(R.id.group_second_text_view);
            groupFirstTextView.setTypeface(null, Typeface.BOLD);
           /* if (isExpanded)
            {
                groupTextView.setTypeface(null, Typeface.BOLD);
            }
            else
            {
                groupTextView.setTypeface(null, Typeface.NORMAL);
            }*/
            groupFirstTextView.setText(groupModelPosition.getGroupName());
            groupSecondTextView.setText(groupModelPosition.getGroupDescription());
        }
        else
        {
            convertView = layoutInflater.inflate(R.layout.group_item, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);

            ((GroupViewHolder) groupViewHolder).setData(groupModelPosition,isExpanded);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

    private class GroupViewHolder
    {
        TextView groupFirstTextView,groupSecondTextView;
        ImageView groupIndicator;

        public GroupViewHolder(View item)
        {
            groupFirstTextView = item.findViewById(R.id.group_first_text_view);
            groupSecondTextView=item.findViewById(R.id.group_second_text_view);
            groupIndicator = item.findViewById(R.id.group_image_view);
        }

        public void setData(GroupModel itemPosition,boolean isExpanded)
        {
            groupFirstTextView.setText(itemPosition.getGroupName());
            groupSecondTextView.setText(itemPosition.getGroupDescription());
            if (isExpanded)
            {
                groupFirstTextView.setTypeface(null, Typeface.BOLD);
                groupIndicator.setImageResource(R.drawable.ic_keyboard_arrow_up_24dp);
            }
            else
            {
                groupFirstTextView.setTypeface(null, Typeface.NORMAL);
                groupIndicator.setImageResource(R.drawable.ic_keyboard_arrow_down_24dp);
            }
        }
    }

    private class ChildViewHolder
    {
        TextView childFirstTextView,childSecondTextView;
        ImageView childImageView;

        public ChildViewHolder(View item)
        {
            childFirstTextView = item.findViewById(R.id.child_first_text_view);
            childSecondTextView=item.findViewById(R.id.child_second_text_view);
            childImageView = item.findViewById(R.id.child_image_view);
        }

        public void setData(ChildModel itemPosition)
        {
            childFirstTextView.setText(itemPosition.getChildName());
            childSecondTextView.setText(itemPosition.getChildDescription());
            childImageView.setImageResource(itemPosition.getChildImage());
        }
    }
}