package apps.buildable.tree;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import apps.buildable.tree.entities.ChildModel;
import apps.buildable.tree.structsure.TreeNode;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamerGigaByte on 4/14/2017.
 */

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.ChildRecyclerViewHolder> {
    private ParentRecyclerViewAdapter parentRecyclerViewAdapter;
    TreeNode<ChildModel> rootNode;

    public ChildRecyclerViewAdapter(TreeNode<ChildModel> rootNode, ParentRecyclerViewAdapter parentRecyclerViewAdapter) {
        this.rootNode = rootNode;
        this.parentRecyclerViewAdapter = parentRecyclerViewAdapter;
    }


    @Override
    public ChildRecyclerViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_child_item, parent, false);

        ChildRecyclerViewHolder vh = new ChildRecyclerViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ChildRecyclerViewHolder holder, final int position) {


        holder.mText.setText(rootNode.children.get(position).data.getName());
        holder.mImage.setImageResource(rootNode.children.get(position).data.getImageRes());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    if (parentRecyclerViewAdapter.rootNode.getCurrentActiveNode() != null)
//                        for (int i=parentRecyclerViewAdapter.rootNode.getCurrentActiveNode().getLevel();i<rootNode.children.get(position).getLevel();i--)
//                        parentRecyclerViewAdapter.notifyItemRangeRemoved(rootNode.children.get(position).getLevel(), parentRecyclerViewAdapter.rootNode.getCurrentActiveNode().children.size());
                    parentRecyclerViewAdapter.rootNode.setCurrentActiveNode(rootNode.children.get(position));
                    parentRecyclerViewAdapter.notifyDataSetChanged();//(parentRecyclerViewAdapter.rootNode.getCurrentActiveNode().getLevel());
                }
                catch (Exception e){e.printStackTrace();}

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return rootNode.children.size();
    }

    static class ChildRecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView mImage;
        @BindView(R.id.text)
        TextView mText;

        ChildRecyclerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
