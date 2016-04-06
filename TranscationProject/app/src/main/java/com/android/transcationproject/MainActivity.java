package com.android.transcationproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FragmentManager mManager;
    private Button addA;
    private Button addB;
    private Button removeA;
    private Button replaceAwithB;
    private Button removeB;
    private Button replaceBwithA;
    private Button attachA;
    private Button deattachA;
    private Button bButton;
    private Fragment FragmentA, FragmentB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mManager = getSupportFragmentManager();
        FragmentA = new FragmentA();
        FragmentB = new FragmentB();


        addA();
        removeA();
        replaceAwithB();
        addB();
        removeB();
        replaceBwithA();
        attachA();
        deaatachA();
        back();

    }

    private void back() {
        bButton = (Button) findViewById(R.id.backButton);

    }

    public void addA() {
        addA = (Button) findViewById(R.id.addA);

        addA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mManager.beginTransaction().add(R.id.group, FragmentA, "A").addToBackStack("addA").commit();

            }
        });

    }

    public void removeA() {
        removeA = (Button) findViewById(R.id.removeA);

        removeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f1 = mManager.findFragmentByTag("A");

                if (f1 != null) {
                    mManager.beginTransaction().remove(f1).addToBackStack("removeA").commit();

                } else {
                    Toast.makeText(getApplicationContext(), "fragment Already empty", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void addB() {
        addB = (Button) findViewById(R.id.addB);

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentB = new FragmentB();
                mManager.beginTransaction().add(R.id.group, FragmentB, "B").addToBackStack("addB").commit();
            }
        });
    }


    public void removeB() {
        removeB = (Button) findViewById(R.id.removeB);
        removeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f2 = mManager.findFragmentByTag("B");
                if (f2 != null) {
                    mManager.beginTransaction().remove(f2).addToBackStack("removeB").commit();

                } else {
                    Toast.makeText(getApplicationContext(), "fragment 2 is already not exsist!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void replaceAwithB() {
        replaceAwithB = (Button) findViewById(R.id.replaceAwithB);

        replaceAwithB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentB = new FragmentB();

                mManager.beginTransaction().replace(R.id.group, FragmentB, "B").addToBackStack("replaceAwithB").commit();

            }
        });


    }


    public void replaceBwithA() {
        replaceBwithA = (Button) findViewById(R.id.replaceBwithA);

        replaceBwithA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA = new FragmentA();

                mManager.beginTransaction().replace(R.id.group, FragmentA, "A").addToBackStack("replaceBwithA").commit();

            }
        });

    }

    public void attachA() {

        attachA = (Button) findViewById(R.id.attachA);

        final FragmentA f1 = (com.android.transcationproject.FragmentA) mManager.findFragmentByTag("A");
        attachA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f1 != null) {
                    mManager.beginTransaction().attach(f1).addToBackStack("attachA").commit();

                }
            }
        });

    }

    public void deaatachA() {
        deattachA = (Button) findViewById(R.id.deattachA);

        attachA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA f1 = (com.android.transcationproject.FragmentA) mManager.findFragmentByTag("A");
                if (f1 != null) {
                    mManager.beginTransaction().detach(f1).addToBackStack("deattachA").commit();

                }
            }
        });


    }
}
