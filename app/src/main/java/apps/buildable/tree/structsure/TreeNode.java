package apps.buildable.tree.structsure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import apps.buildable.tree.entities.ChildModel;


/**
 * Created by SamerGigaByte on 4/14/2017.
 */

public class TreeNode<T> implements Iterable<TreeNode<T>> {

    public T data;
    public TreeNode<T> parent;
    public LinkedList<TreeNode<T>> children;
    private int mCurrentCount;
    private TreeNode<ChildModel> mCurrentActiveNode;


    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    private List<TreeNode<T>> elementsIndex;

    public TreeNode(T data) {
        this.data = data;
        this.children = new LinkedList<TreeNode<T>>();
        this.elementsIndex = new LinkedList<TreeNode<T>>();
        this.elementsIndex.add(this);
    }

    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        this.registerChildForSearch(childNode);
        return childNode;
    }

    public int getLevel() {
        if (this.isRoot())
            return 0;
        else
            return parent.getLevel() + 1;
    }

    private void registerChildForSearch(TreeNode<T> node) {
        elementsIndex.add(node);
        if (parent != null)
            parent.registerChildForSearch(node);
    }

    public TreeNode<T> findTreeNode(Comparable<T> cmp) {
        for (TreeNode<T> element : this.elementsIndex) {
            T elData = element.data;
            if (cmp.compareTo(elData) == 0)
                return element;
        }

        return null;
    }

    @Override
    public String toString() {
        return data != null ? data.toString() : "[data null]";
    }

    @Override
    public Iterator<TreeNode<T>> iterator() {
        TreeNodeIterator<T> iter = new TreeNodeIterator<T>(this);
        return iter;
    }

    public int getMaxLevel() {
        int maxLevel = getLevel();

        if (children != null) {
            for (TreeNode node : children) {
                int nodeMaxLeve = node.getMaxLevel();
                maxLevel = nodeMaxLeve > maxLevel ? nodeMaxLeve : maxLevel;
            }
        }
        return maxLevel;

    }

    public LinkedList<TreeNode<T>> getChildren() {
        return children;
    }

    public void setCurrentActiveNode(TreeNode<ChildModel> currentActiveNode) {
        mCurrentActiveNode = currentActiveNode;
    }

    public TreeNode<ChildModel> getCurrentActiveNode() {
        return mCurrentActiveNode;
    }

    public TreeNode getParentAtLevel(int level) {
        if (level == getLevel()) {
            return this;
        }
        return parent.getParentAtLevel(level);

    }
}