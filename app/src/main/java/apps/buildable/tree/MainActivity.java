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

        ChildModelBuilder childModelBuilder = new ChildModelBuilder();

        childModelBuilder.setName("Root").setImageRes(R.mipmap.ic_launcher_round);

        TreeNode<ChildModel> rootNode = new TreeNode<>(childModelBuilder.createChildModel());
        fillTree(childModelBuilder, rootNode);


        Log.wtf("Max Level", rootNode.getMaxLevel() + "");

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
            }
        });

        mLayoutManager = new LinearLayoutManager(this);
        mParentRecyclerView.setLayoutManager(mLayoutManager);
        mParentRecyclerView.setAdapter(new ParentRecyclerViewAdapter(rootNode, mParentRecyclerView));

    }

    private void fillTree(ChildModelBuilder childModelBuilder, TreeNode<ChildModel> rootNode) {
        ChildModel a1 = childModelBuilder.setName("A1").createChildModel();

        TreeNode l1 = rootNode.addChild(a1);
        l1.setCurrentActiveNode(l1);

        ChildModel b1 = childModelBuilder.setName("b1").createChildModel();
        l1.addChild(b1);

        ChildModel b2 = childModelBuilder.setName("b2").createChildModel();
        TreeNode l2 = l1.addChild(b2);

        ChildModel b3 = childModelBuilder.setName("b3").createChildModel();
        l1.addChild(b3);

        ChildModel c1 = childModelBuilder.setName("C1").createChildModel();
        l2.addChild(c1);

        ChildModel c2 = childModelBuilder.setName("C2").createChildModel();
        TreeNode l3 = l2.addChild(c2);

        ChildModel d1 = childModelBuilder.setName("D2").createChildModel();
        l3.addChild(d1);

        ChildModel d2 = childModelBuilder.setName("D2").createChildModel();
        TreeNode l4 = l3.addChild(d2);

        ChildModel d3 = childModelBuilder.setName("D3").createChildModel();
        l3.addChild(d3);

        ChildModel e1 = childModelBuilder.setName("E1").createChildModel();
        TreeNode l5 = l4.addChild(e1);
        ChildModel e2 = childModelBuilder.setName("E2").createChildModel();
        l4.addChild(e2);
        ChildModel e3 = childModelBuilder.setName("E3").createChildModel();
        l4.addChild(e3);

        ChildModel F1 = childModelBuilder.setName("F1").createChildModel();
        l5.addChild(F1);
        ChildModel f2 = childModelBuilder.setName("F2").createChildModel();
        l5.addChild(f2);
        ChildModel f3 = childModelBuilder.setName("F3").createChildModel();
        l5.addChild(f3);


    }


}
