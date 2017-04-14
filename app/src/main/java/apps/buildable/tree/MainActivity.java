package apps.buildable.tree;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.UUID;

import apps.buildable.tree.entities.ChildModel;
import apps.buildable.tree.entities.ChildModelBuilder;
import apps.buildable.tree.structsure.TreeNode;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.parent_recycler_view)
    RecyclerView mParentRecyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ChildModelBuilder childModelBuilder=new ChildModelBuilder();

        childModelBuilder.setName("Root").setImageRes(R.mipmap.ic_launcher_round);

        TreeNode<ChildModel> rootNode=new TreeNode<>(childModelBuilder.createChildModel());
        fillTree(childModelBuilder, rootNode);


        Log.wtf("Max Level",rootNode.getMaxLevel()+"");

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
            }
        });

        mLayoutManager = new LinearLayoutManager(this);
        mParentRecyclerView.setLayoutManager(mLayoutManager);
        mParentRecyclerView.setAdapter(new ParentRecyclerViewAdapter(rootNode));

    }

    private void fillTree(ChildModelBuilder childModelBuilder, TreeNode<ChildModel> rootNode) {
        ChildModel leaf1= childModelBuilder.setName("Leaf1").createChildModel();

        TreeNode leaf1Node=rootNode.addChild(leaf1);
        ChildModel leaf1_1= childModelBuilder.setName("Leaf1_1").createChildModel();
        leaf1Node.addChild(leaf1_1);
        ChildModel leaf1_2= childModelBuilder.setName("Leaf1_2").createChildModel();
        leaf1Node.addChild(leaf1_2);


        ChildModel leaf2= childModelBuilder.setName("Leaf2").createChildModel();
        TreeNode leaf2Node=rootNode.addChild(leaf2);

        ChildModel leaf2_1= childModelBuilder.setName("Leaf2_1").createChildModel();
        leaf2Node.addChild(leaf2_1);
        ChildModel leaf2_2= childModelBuilder.setName("Leaf2_2").createChildModel();
        leaf2Node.addChild(leaf2_2);


        ChildModel leaf3= childModelBuilder.setName("Leaf3").createChildModel();
        TreeNode leaf3Node=rootNode.addChild(leaf3);

        ChildModel leaf3_1= childModelBuilder.setName("Leaf3_1").createChildModel();
        leaf3Node.addChild(leaf3_1);
        ChildModel leaf3_2= childModelBuilder.setName("Leaf3_2").createChildModel();
        leaf3Node.addChild(leaf3_2);


        ChildModel leaf4= childModelBuilder.setName("Leaf4").createChildModel();
        TreeNode leaf4Node=rootNode.addChild(leaf4);


        ChildModel leaf4_1= childModelBuilder.setName("Leaf4_1").createChildModel();
        leaf4Node.addChild(leaf4_1);
        ChildModel leaf4_2= childModelBuilder.setName("Leaf4_2").createChildModel();
        TreeNode leaf4_2Node=leaf4Node.addChild(leaf4_2);

        ChildModel leaf4_2_1= childModelBuilder.setName("Leaf4_2_1").createChildModel();

        TreeNode leaf4_2_1Node= leaf4_2Node.addChild(leaf4_2_1);
        ChildModel leaf4_2_1_1= childModelBuilder.setName("Leaf4_2_1_1").createChildModel();
        TreeNode leaf4_2_1_1Node=leaf4_2_1Node.addChild(leaf4_2_1_1);

        ChildModel leaf4_2_1_1_1= childModelBuilder.setName("Leaf4_2_1_1_1").createChildModel();
        TreeNode leaf4_2_1_1_1Node=leaf4_2_1_1Node.addChild(leaf4_2_1_1_1);
        ChildModel leaf4_2_1_1_1_1= childModelBuilder.setName("leaf4_2_1_1_1_1").createChildModel();
        leaf4_2_1_1_1Node.addChild(leaf4_2_1_1_1_1);

        for(int i=0;i<90;i++){
            ChildModel rand= childModelBuilder.setName(new UUID(2,1).toString()).createChildModel();
            ChildModel rand2= childModelBuilder.setName(new UUID(2,1).toString()).createChildModel();
            leaf4_2_1_1_1Node.addChild(rand);
            leaf4_2_1_1_1Node=leaf4_2_1_1_1Node.addChild(rand2);

        }
    }


}
