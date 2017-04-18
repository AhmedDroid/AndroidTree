package apps.buildable.tree;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
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
    public TreeNode<ChildModel> rootNode, activeNode;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView main;

    public ParentRecyclerViewAdapter(TreeNode<ChildModel> rootNode, RecyclerView main) {
        this.rootNode = rootNode;
        this.rootNode.setCurrentActiveNode(rootNode.children.get(0));
        this.main = main;
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
            activeNode = rootNode.getCurrentActiveNode().getParentAtLevel(position);

            holder.mChildRecyclerView.setAdapter(
                    new ChildRecyclerViewAdapter(activeNode, this)

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

            LinearSnapHelper s = new LinearSnapHelper() {

                @Override
                public View findSnapView(RecyclerView.LayoutManager layoutManager) {
                    View centerView = super.findSnapView(layoutManager);
                    if (centerView == null)
                        return super.findSnapView(layoutManager);

                    int position = layoutManager.getPosition(centerView);
                    //  centerViewPosition = position;
                    if (position < activeNode.children.size()) {
                        while (!ParentRecyclerViewAdapter.this.main.isComputingLayout()) {
                            ParentRecyclerViewAdapter.this.rootNode.setCurrentActiveNode(activeNode.children.get(position));
                            ParentRecyclerViewAdapter.this.notifyItemChanged(0);
                            break;
                        }
                        } else {

                    }
//                int targetPosition = -1;
//                if (layoutManager.canScrollHorizontally()) {
//                    if (velocityX < 0) {
//                        targetPosition = position - 1;
//                    } else {
//                        targetPosition = position + 1;
//                    }
//                }
//
//                if (layoutManager.canScrollVertically()) {
//                    if (velocityY < 0) {
//                        targetPosition = position - 1;
//                    } else {
//                        targetPosition = position + 1;
//                    }
//                }

//                final int firstItem = 0;
//                final int lastItem = layoutManager.getItemCount() - 1;
//                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
                    //  get(position, 5);
                    return centerView;
                }

                @Override
                public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                    View centerView = findSnapView(layoutManager);
                    if (centerView == null)
                        return RecyclerView.NO_POSITION;

                    int position = layoutManager.getPosition(centerView);
                    int targetPosition = -1;
                    if (layoutManager.canScrollHorizontally()) {
                        if (velocityX < 0) {
                            targetPosition = position - 1;

                        } else {
                            targetPosition = position + 1;
                        }
                    }

                    if (layoutManager.canScrollVertically()) {
                        if (velocityY < 0) {
                            targetPosition = position - 1;
                        } else {
                            targetPosition = position + 1;
                        }
                    }

                    final int firstItem = 0;
                    final int lastItem = layoutManager.getItemCount() - 1;
                    targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
//                get(targetPosition, 5);
                    return targetPosition;
                }
            };
            s.attachToRecyclerView(mChildRecyclerView);

        }
    }
}


