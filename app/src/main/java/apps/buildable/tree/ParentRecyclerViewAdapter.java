package apps.buildable.tree;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import apps.buildable.tree.entities.ChildModel;
import apps.buildable.tree.structsure.TreeNode;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SamerGigaByte on 4/14/2017.
 */

public class ParentRecyclerViewAdapter extends RecyclerView.Adapter<ParentRecyclerViewAdapter.ParentViewHolder> {
    public TreeNode<ChildModel> rootNode;
    private LinearLayoutManager mLayoutManager;

    public ParentRecyclerViewAdapter(TreeNode<ChildModel> rootNode) {
        this.rootNode = rootNode;
        this.rootNode.setCurrentActiveNode(null);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ParentViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_parent_item, parent, false);

        ParentViewHolder vh = new ParentViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ParentViewHolder holder, int position) {

        if (rootNode.getCurrentActiveNode() != null) {
            TreeNode treeNode = rootNode.getCurrentActiveNode().getParentAtLevel(position);

            holder.mChildRecyclerView.setAdapter(
                    new ChildRecyclerViewAdapter(treeNode, this)

            );

        } else {
            holder.mChildRecyclerView.setAdapter(new ChildRecyclerViewAdapter(rootNode, this));
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return rootNode.getCurrentActiveNode() == null ? rootNode.getLevel() + 1 : rootNode.getCurrentActiveNode().getLevel() + 1;
    }

    class ParentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.child_recycler_view)
        RecyclerView mChildRecyclerView;

        ParentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            mLayoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            mChildRecyclerView.setLayoutManager(mLayoutManager);

        }
    }
}


