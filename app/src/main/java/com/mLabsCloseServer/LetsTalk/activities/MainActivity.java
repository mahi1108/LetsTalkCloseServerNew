package com.mLabsCloseServer.LetsTalk.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amirarcane.lockscreen.activity.EnterPinActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mLabsCloseServer.LetsTalk.fragments.AdminUsersFragment;
import com.mLabsCloseServer.LetsTalk.models.User2;
import com.mLabsCloseServer.LetsTalk.models.UserNew;
import com.mLabsCloseServer.LetsTalk.utils.SharedPreferenceHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.sinch.android.rtc.calling.Call;
import com.mLabsCloseServer.LetsTalk.R;
import com.mLabsCloseServer.LetsTalk.adapters.MenuUsersRecyclerAdapter;
import com.mLabsCloseServer.LetsTalk.adapters.ViewPagerAdapter;
import com.mLabsCloseServer.LetsTalk.fragments.GroupCreateDialogFragment;
import com.mLabsCloseServer.LetsTalk.fragments.MyCallsFragment;
import com.mLabsCloseServer.LetsTalk.fragments.MyGroupsFragment;
import com.mLabsCloseServer.LetsTalk.fragments.MyUsersFragment;
import com.mLabsCloseServer.LetsTalk.fragments.OptionsFragment;
import com.mLabsCloseServer.LetsTalk.fragments.UserSelectDialogFragment;
import com.mLabsCloseServer.LetsTalk.interfaces.ContextualModeInteractor;
import com.mLabsCloseServer.LetsTalk.interfaces.HomeIneractor;
import com.mLabsCloseServer.LetsTalk.interfaces.ChatItemClickListener;
import com.mLabsCloseServer.LetsTalk.interfaces.UserGroupSelectionDismissListener;
import com.mLabsCloseServer.LetsTalk.models.Contact;
import com.mLabsCloseServer.LetsTalk.models.Group;
import com.mLabsCloseServer.LetsTalk.models.Message;
import com.mLabsCloseServer.LetsTalk.models.User;
import com.mLabsCloseServer.LetsTalk.services.FetchMyUsersService;
import com.mLabsCloseServer.LetsTalk.utils.ConfirmationDialogFragment;
import com.mLabsCloseServer.LetsTalk.utils.Helper;
import com.mLabsCloseServer.LetsTalk.views.SwipeControlViewPager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.realm.RealmResults;

public class MainActivity extends BaseActivity implements HomeIneractor, ChatItemClickListener, View.OnClickListener, ContextualModeInteractor, UserGroupSelectionDismissListener {
    private static final int REQUEST_CODE_CHAT_FORWARD = 99;
    private final int CONTACTS_REQUEST_CODE = 321;
    private static String USER_SELECT_TAG = "userselectdialog";
    private static String OPTIONS_MORE = "optionsmore";
    private static String GROUP_CREATE_TAG = "groupcreatedialog";
    private static String CONFIRM_TAG = "confirmtag";

    private ImageView usersImage, backImage, dialogUserImage;
    private RecyclerView menuRecyclerView;
    private SwipeRefreshLayout swipeMenuRecyclerView;
    private FlowingDrawer drawerLayout;
    private EditText searchContact;
    private TextView selectedCount;
    //private TextView invite;
    private RelativeLayout toolbarContainer, cabContainer;

    private TabLayout tabLayout;
    private int LAST_TABED_POSITION;
    private SwipeControlViewPager viewPager;

    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;

    private MenuUsersRecyclerAdapter menuUsersRecyclerAdapter;
    private HashMap<String, Contact> contactsData;
    private ArrayList<User> myUsers = new ArrayList<>();
    private ArrayList<Group> myGroups = new ArrayList<>();
    private ArrayList<Message> messageForwardList = new ArrayList<>();
    private UserSelectDialogFragment userSelectDialogFragment;
    private ViewPagerAdapter adapter;
    private  boolean isAuthenticated = false;
    private boolean isAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();

        SharedPreferenceHelper prefHelper = SharedPreferenceHelper.getInstance(getApplicationContext());
        User2 myAccount = prefHelper.getUserInfo();
        if(myAccount.lockScreen.equals("101")) {
            Intent intent = new Intent(getApplicationContext(), EnterPinActivity.class);
            intent.putExtra("email", myAccount.email);
            startActivity(intent);
        }

        //setup recyclerview in drawer layout
        contactsData = helper.getMyUsersNameCache();
        setupMenu();

        //If its a url then load it, else Make a text drawable of user's name
        setProfileImage(usersImage);
        usersImage.setOnClickListener(this);
        backImage.setOnClickListener(this);
        //invite.setOnClickListener(this);
        findViewById(R.id.action_delete).setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
        floatingActionButton.setVisibility(View.VISIBLE);

        setupViewPager();
        LAST_TABED_POSITION = 1;

        RealmResults<User> myUsers = rChatDb.where(User.class).notEqualTo("id", userMe.getId()).findAll();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        if (myUsers != null && !myUsers.isEmpty()) {
            myUsersResult(new ArrayList<User>(rChatDb.copyFromRealm(myUsers)));
        } else {
            refreshMyContacts();
        }

        //markOnline(true);
        updateFcmToken();

        checkAuthenticatedOrNot();
    }

    public static String global_organization_name  = "";

    private  void checkAuthenticatedOrNot()
    {
        Log.i("lets_talk","******"+userMe.getId()+"*******");

        FirebaseDatabase dBase = FirebaseDatabase.getInstance();
        DatabaseReference dRef = dBase.getReference(Helper.REF_USERS);
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> itr_user_ids =  dataSnapshot.getChildren();
                Iterator<DataSnapshot> it_user_ids = itr_user_ids.iterator();
                while (it_user_ids.hasNext()){

                    DataSnapshot user_ds = it_user_ids.next();
                    String user_id = user_ds.getKey();

                    if(user_id.equalsIgnoreCase(userMe.getId())){
                        Iterable<DataSnapshot> itr_user_id_childs = user_ds.getChildren();
                        Iterator<DataSnapshot> it_user_id_childs = itr_user_id_childs.iterator();

                        while (it_user_id_childs.hasNext()){
                            DataSnapshot ds_child_mno =  it_user_id_childs.next();
                            if(ds_child_mno.getKey().equalsIgnoreCase("authentication")){
                                isAuthenticated = Boolean.parseBoolean(ds_child_mno.getValue().toString());
                            }

                            if(ds_child_mno.getKey().equalsIgnoreCase("admin")){
                                isAdmin = Boolean.parseBoolean(ds_child_mno.getValue().toString());
                            }

                            if(ds_child_mno.getKey().equalsIgnoreCase("orgName")){
                                global_organization_name = ds_child_mno.getValue().toString();
                            }


                        }  // loop will repeat all the child elements of UserId / Mno.

                        if(!isAuthenticated && !isAdmin){
                            AlertDialog.Builder aDialog = new AlertDialog.Builder(MainActivity.this);
                            aDialog.setTitle("Message");
                            aDialog.setMessage("Your Authentication is Pending, Please contact to Admin.");
                            aDialog.setCancelable(false);
                            aDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                            aDialog.show();
                        }else{

                            if(isAdmin && isAuthenticated){
                                    getListOfNonAuthenticatedUsers(global_organization_name);
                            }
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getListOfNonAuthenticatedUsers(final String orgName)
    {

        Log.i("lets_talk","******"+userMe.getId()+"*******");

        FirebaseDatabase dBase = FirebaseDatabase.getInstance();
        DatabaseReference dRef = dBase.getReference(Helper.REF_USERS);
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<UserNew> list = new ArrayList<>();

                Iterable<DataSnapshot> itr_user_ids =  dataSnapshot.getChildren();
                Iterator<DataSnapshot> it_user_ids = itr_user_ids.iterator();
                while (it_user_ids.hasNext()){

                    UserNew uNew = new UserNew();

                    DataSnapshot user_ds = it_user_ids.next();
                    String user_id = user_ds.getKey();

                        Iterable<DataSnapshot> itr_user_id_childs = user_ds.getChildren();
                        Iterator<DataSnapshot> it_user_id_childs = itr_user_id_childs.iterator();

                        while (it_user_id_childs.hasNext()){
                            DataSnapshot ds_child_mno =  it_user_id_childs.next();

                            Log.i("lets_talk",
                                    "Key : "+ds_child_mno.getKey()+"\n" +
                                            "Value : "+ds_child_mno.getValue());

                            switch (ds_child_mno.getKey()){
                                case "admin":
                                    uNew.setAdmin(Boolean.parseBoolean(
                                                        ds_child_mno.getValue().toString()));
                                    break;
                                case "authentication":
                                    uNew.setAuthentication(Boolean.parseBoolean(
                                            ds_child_mno.getValue().toString()));
                                    break;
                                case "contactName":
                                    uNew.setContactName(
                                            ds_child_mno.getValue().toString());
                                    break;
                                case "dept":
                                    uNew.setDept(ds_child_mno.getValue().toString());
                                    break;
                                case "id":
                                    uNew.setId(ds_child_mno.getValue().toString());
                                    break;
                                case "image":
                                    uNew.setImage(
                                            ds_child_mno.getValue().toString());
                                    break;
                                case "inviteAble":
                                    uNew.setInviteAble(Boolean.parseBoolean(
                                            ds_child_mno.getValue().toString()));
                                    break;
                                case "name":
                                    uNew.setName(
                                            ds_child_mno.getValue().toString());
                                    break;
                                case "nameToDisplay":
                                    uNew.setNameToDisplay(
                                            ds_child_mno.getValue().toString());
                                    break;
                                case "online":
                                    uNew.setOnline(Boolean.parseBoolean(
                                            ds_child_mno.getValue().toString()));
                                    break;
                                case "orgName":
                                    uNew.setOrgName(
                                            ds_child_mno.getValue().toString());
                                    break;
                                case "selected":
                                    uNew.setSelected(Boolean.parseBoolean(
                                            ds_child_mno.getValue().toString()));
                                    break;
                                case "status":
                                    uNew.setStatus(ds_child_mno.getValue().toString());
                                    break;
                                case "typing":
                                    uNew.setTyping(Boolean.parseBoolean(
                                            ds_child_mno.getValue().toString()));
                                    break;
                                default:
                                    Log.i("lets_talk","Default Block Executed...");
                                    break;
                            }
                        }  // loop will repeat all the child elements of UserId / Mno.

                        Log.i("lets_talk","uNew Object : "+uNew);
                        if(uNew != null) {
                            Log.i("lets_talk", "Org Name : " +
                                    uNew.getOrgName());
                        }

                        if(uNew.getOrgName().equalsIgnoreCase(orgName) &&
                                !uNew.isAdmin() &&
                                !uNew.isAuthentication()){
                            list.add(uNew);
                        }
                }

                if(list.size() > 0){
                    setNonAuthUsersDataOnRView(list);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public  void setNonAuthUsersDataOnRView(ArrayList<UserNew> list)
    {
        AdminUsersFragment.users_list = list;

        android.app.FragmentManager fManager = getFragmentManager();
        FragmentTransaction  tx = fManager.beginTransaction();
        tx.replace(R.id.main_frame,new AdminUsersFragment()) ;
        tx.addToBackStack("true");
        tx.commit();
    }


    private void initUi() {
        usersImage = findViewById(R.id.users_image);
        menuRecyclerView = findViewById(R.id.menu_recycler_view);
        swipeMenuRecyclerView = findViewById(R.id.menu_recycler_view_swipe_refresh);
        drawerLayout = findViewById(R.id.drawer_layout);
        searchContact = findViewById(R.id.searchContact);
        //invite = findViewById(R.id.invite);
        toolbarContainer = findViewById(R.id.toolbarContainer);
        cabContainer = findViewById(R.id.cabContainer);
        selectedCount = findViewById(R.id.selectedCount);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        floatingActionButton = findViewById(R.id.addConversation);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        backImage = findViewById(R.id.back_button);
        drawerLayout.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
    }

    private void updateFcmToken() {
        fcmIdRef.child(userMe.getId()).setValue(FirebaseInstanceId.getInstance().getToken());
    }



    private void setupViewPager() {
        final int[] tabIcons = {
                R.drawable.chatbuttonselected,
                R.drawable.avatarbuttonselected,
                R.drawable.ic_phone,
                R.drawable.chatbuttonlite,
                R.drawable.avatarbuttonlite,
                R.drawable.ic_call_unselected
        };

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new MyUsersFragment(), "");
        adapter.addFrag(new MyGroupsFragment(), "");
        adapter.addFrag(new MyCallsFragment(), "");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    tab.setIcon(tabIcons[0]);
                    LAST_TABED_POSITION = 0;
                } else if (tab.getPosition() == 1){
                    tab.setIcon(tabIcons[1]);
                    LAST_TABED_POSITION = 1;
                }else if (tab.getPosition() == 2){
                    tab.setIcon(tabIcons[2]);
                    LAST_TABED_POSITION = 2;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    tab.setIcon(tabIcons[3]);
                } else if (tab.getPosition() == 1){
                    tab.setIcon(tabIcons[4]);
                }else if (tab.getPosition() == 2){
                    tab.setIcon(tabIcons[5]);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    tab.setIcon(tabIcons[0]);
                    LAST_TABED_POSITION = 0;
                } else if (tab.getPosition() == 1){
                    tab.setIcon(tabIcons[1]);
                    LAST_TABED_POSITION = 1;
                }else if (tab.getPosition() == 2){
                    tab.setIcon(tabIcons[2]);
                    LAST_TABED_POSITION = 2;
                }
            }
        });
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[4]);
        tabLayout.getTabAt(2).setIcon(tabIcons[5]);

    }

    private void setupMenu() {
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuUsersRecyclerAdapter = new MenuUsersRecyclerAdapter(this, myUsers);
        menuRecyclerView.setAdapter(menuUsersRecyclerAdapter);
        swipeMenuRecyclerView.setColorSchemeResources(R.color.colorAccent);
        swipeMenuRecyclerView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMyContacts();
            }
        });
        searchContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                menuUsersRecyclerAdapter.getFilter().filter(editable.toString());
            }
        });
    }

    private void setProfileImage(ImageView imageView) {
        if (userMe != null)
            Glide.with(this).load(userMe.getImage()).apply(new RequestOptions().placeholder(R.drawable.letstalk_placeholder)).into(imageView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CONTACTS_REQUEST_CODE:
                refreshMyContacts();
                break;
        }
    }


    private void refreshMyContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            if (!FetchMyUsersService.STARTED) {
                if (!swipeMenuRecyclerView.isRefreshing())
                    swipeMenuRecyclerView.setRefreshing(true);
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser != null) {
                    firebaseUser.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        @Override
                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                            if (task.isSuccessful()) {
                                String idToken = task.getResult().getToken();
                                FetchMyUsersService.startMyUsersService(MainActivity.this, userMe.getId(), idToken);
                            }
                        }
                    });
                }
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS}, CONTACTS_REQUEST_CODE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //markOnline(false);
    }

    @Override
    public void onBackPressed() {
        if (ElasticDrawer.STATE_CLOSED != drawerLayout.getDrawerState()) {
            drawerLayout.closeMenu(true);
        } else if (isContextualMode()) {
            disableContextualMode();
        } else if (viewPager.getCurrentItem() != 0) {
            viewPager.post(new Runnable() {
                @Override
                public void run() {
                    viewPager.setCurrentItem(0);
                }
            });
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (REQUEST_CODE_CHAT_FORWARD):
                if (resultCode == Activity.RESULT_OK) {
                    //show forward dialog to choose users
                    messageForwardList.clear();
                    ArrayList<Message> temp = data.getParcelableArrayListExtra("FORWARD_LIST");
                    messageForwardList.addAll(temp);
                    userSelectDialogFragment = UserSelectDialogFragment.newInstance(this, myUsers);
                    FragmentManager manager = getSupportFragmentManager();
                    Fragment frag = manager.findFragmentByTag(USER_SELECT_TAG);
                    if (frag != null) {
                        manager.beginTransaction().remove(frag).commit();
                    }
                    userSelectDialogFragment.show(manager, USER_SELECT_TAG);
                }
                break;
        }
    }

    private void sortMyGroupsByName() {
        Collections.sort(myGroups, new Comparator<Group>() {
            @Override
            public int compare(Group group1, Group group2) {
                return group1.getName().compareToIgnoreCase(group2.getName());
            }
        });
    }

    private void sortMyUsersByName(ArrayList<User> users) {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user1.getNameToDisplay().compareToIgnoreCase(user2.getNameToDisplay());
            }
        });
    }

    @Override
    void userAdded(User value) {
        if (value.getId().equals(userMe.getId()))
            return;
        int existingPos = myUsers.indexOf(value);
        if (existingPos != -1) {
            myUsers.remove(existingPos);
            menuUsersRecyclerAdapter.notifyItemRemoved(existingPos);
        }
        myUsers.add(0, value);
        menuUsersRecyclerAdapter.notifyItemInserted(0);
        refreshUsers(-1);
    }

    @Override
    void groupAdded(Group group) {
        if (!myGroups.contains(group)) {
            myGroups.add(group);
            sortMyGroupsByName();
        }
    }

    @Override
    void userUpdated(User value) {
        if (value.getId().equals(userMe.getId())) {
            userMe = helper.getLoggedInUser();
            setProfileImage(usersImage);
            FragmentManager manager = getSupportFragmentManager();
            Fragment frag = manager.findFragmentByTag(OPTIONS_MORE);
            if (frag != null) {
                ((OptionsFragment) frag).setUserDetails();
            }
        } else {
            int existingPos = myUsers.indexOf(value);
            if (existingPos != -1) {
                myUsers.set(existingPos, value);
                menuUsersRecyclerAdapter.notifyItemChanged(existingPos);
                refreshUsers(existingPos);
            }
        }
    }

    @Override
    void groupUpdated(Group group) {
        int existingPos = myGroups.indexOf(group);
        if (existingPos != -1) {
            myGroups.set(existingPos, group);
            //menuUsersRecyclerAdapter.notifyItemChanged(existingPos);
            //refreshUsers(existingPos);
        }
    }

    @Override
    void onSinchConnected() {

    }

    @Override
    void onSinchDisconnected() {

    }

    @Override
    public void onChatItemClick(String chatId, String chatName, int position, View userImage) {
        openChat(ChatActivity.newIntent(this, messageForwardList, chatId, chatName), userImage);
    }

    @Override
    public void onChatItemClick(Group group, int position, View userImage) {
        openChat(ChatActivity.newIntent(this, messageForwardList, group), userImage);
    }

    @SuppressLint("RestrictedApi")
    private void openChat(Intent intent, View userImage) {
        if (ElasticDrawer.STATE_CLOSED != drawerLayout.getDrawerState()) {
            drawerLayout.closeMenu(true);
        }
        if (userImage == null) {
            userImage = usersImage;
        }

        if (Build.VERSION.SDK_INT > 21) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, userImage, "backImage");
            startActivityForResult(intent, REQUEST_CODE_CHAT_FORWARD, options.toBundle());
        } else {
            startActivityForResult(intent, REQUEST_CODE_CHAT_FORWARD);
            overridePendingTransition(0, 0);
        }

        if (userSelectDialogFragment != null)
            userSelectDialogFragment.dismiss();
    }

    private void refreshUsers(int pos) {
        Fragment frag = getSupportFragmentManager().findFragmentByTag(USER_SELECT_TAG);
        if (frag != null) {
            userSelectDialogFragment.refreshUsers(pos);
        }
    }

    private void markOnline(boolean b) {
        //Mark online boolean as b in firebase
        usersRef.child(userMe.getId()).child("online").setValue(b);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                drawerLayout.openMenu(true);
                break;
            case R.id.addConversation:
                switch (viewPager.getCurrentItem()) {
                    case 0:
                        drawerLayout.openMenu(true);
                        break;
                    case 1:
                        GroupCreateDialogFragment.newInstance(this, userMe, myUsers).show(getSupportFragmentManager(), GROUP_CREATE_TAG);
                        break;
                    case 2:
                        drawerLayout.openMenu(true);
                        break;
                }
                break;
            case R.id.users_image:
                if (userMe != null)
                    OptionsFragment.newInstance(getSinchServiceInterface()).show(getSupportFragmentManager(), OPTIONS_MORE);
                break;
//            case R.id.invite:
//                try {
//                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                    shareIntent.setType("text/plain");
//                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, String.format(getString(R.string.invitation_title), getString(R.string.app_name)));
//                    shareIntent.putExtra(Intent.EXTRA_TEXT, String.format(getString(R.string.invitation_message), getString(R.string.app_name), getPackageName()));
//                    startActivity(Intent.createChooser(shareIntent, getString(R.string.invitation_share_title)));
//                } catch (Exception ignored) {
//
//                }
//                break;
            case R.id.action_delete:
                FragmentManager manager = getSupportFragmentManager();
                Fragment frag = manager.findFragmentByTag(CONFIRM_TAG);
                if (frag != null) {
                    manager.beginTransaction().remove(frag).commit();
                }

                ConfirmationDialogFragment confirmationDialogFragment = ConfirmationDialogFragment.newInstance(getString(R.string.delete_chat_title),
                        getString(R.string.delete_chat_message),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ((MyUsersFragment) adapter.getItem(0)).deleteSelectedChats();
                                ((MyGroupsFragment) adapter.getItem(1)).deleteSelectedChats();
                                disableContextualMode();
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                disableContextualMode();
                            }
                        });
                confirmationDialogFragment.show(manager, CONFIRM_TAG);
                break;
        }
    }

    @Override
    public void placeCall(boolean callIsVideo, User user) {
        if (permissionsAvailable(permissionsSinch)) {
            try {
                Call call = callIsVideo ? getSinchServiceInterface().callUserVideo(user.getId()) : getSinchServiceInterface().callUser(user.getId());
                if (call == null) {
                    // Service failed for some reason, show a Toast and abort
                    Toast.makeText(this, "Service is not started. Try stopping the service and starting it again before placing a call.", Toast.LENGTH_LONG).show();
                    return;
                }
                String callId = call.getCallId();
                startActivity(CallScreenActivity.newIntent(this, user, callId, "OUT"));
            } catch (Exception e) {
                Log.e("CHECK", e.getMessage());
                //ActivityCompat.requestPermissions(this, new String[]{e.getRequiredPermission()}, 0);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissionsSinch, 69);
        }
    }

    @Override
    public void onUserGroupSelectDialogDismiss() {
        messageForwardList.clear();
//        if (helper.getSharedPreferenceHelper().getBooleanPreference(Helper.GROUP_CREATE, false)) {
//            helper.getSharedPreferenceHelper().setBooleanPreference(Helper.GROUP_CREATE, false);
//            GroupCreateDialogFragment.newInstance(this, userMe, myUsers).show(getSupportFragmentManager(), GROUP_CREATE_TAG);
//        }
    }

    @Override
    public void selectionDismissed() {
        //do nothing..
    }

    @Override
    public void myUsersResult(ArrayList<User> myUsers) {
        this.myUsers.clear();
        this.myUsers.addAll(myUsers);
        sortMyUsersByName(this.myUsers);

        if (!contactsData.isEmpty()) {
            HashMap<String, Contact> tempContactData = new HashMap<>(contactsData);
            for (User user : this.myUsers) {
                tempContactData.remove(Helper.getEndTrim(user.getId()));
            }
            ArrayList<User> inviteAble = new ArrayList<>();
            for (Map.Entry<String, Contact> contactEntry : tempContactData.entrySet()) {
                inviteAble.add(new User(contactEntry.getValue().getPhoneNumber(), contactEntry.getValue().getName()));
            }
            if (!inviteAble.isEmpty()) {
                inviteAble.add(0, new User("-1", "-1"));
            }
            //sortMyUsersByName(inviteAble);
            this.myUsers.addAll(inviteAble);
        }

        refreshUsers(-1);
        menuUsersRecyclerAdapter.notifyDataSetChanged();
        swipeMenuRecyclerView.setRefreshing(false);
    }

    @Override
    public void myContactsResult(HashMap<String, Contact> myContacts) {
        contactsData.clear();
        contactsData.putAll(myContacts);
        MyUsersFragment myUsersFragment = ((MyUsersFragment) adapter.getItem(0));
        if (myUsersFragment != null) myUsersFragment.setUserNamesAsInPhone();
        MyCallsFragment myCallsFragment = ((MyCallsFragment) adapter.getItem(2));
        if (myCallsFragment != null) myCallsFragment.setUserNamesAsInPhone();
    }

    public void disableContextualMode() {
        cabContainer.setVisibility(View.GONE);
        toolbarContainer.setVisibility(View.VISIBLE);
        ((MyUsersFragment) adapter.getItem(0)).disableContextualMode();
        ((MyGroupsFragment) adapter.getItem(1)).disableContextualMode();
        viewPager.setSwipeAble(true);
    }

    @Override
    public void enableContextualMode() {
        cabContainer.setVisibility(View.VISIBLE);
        toolbarContainer.setVisibility(View.GONE);
        viewPager.setSwipeAble(false);
    }

    @Override
    public boolean isContextualMode() {
        return cabContainer.getVisibility() == View.VISIBLE;
    }

    @Override
    public void updateSelectedCount(int count) {
        if (count > 0) {
            selectedCount.setText(String.format(getString(R.string.selected_count), count));
        } else {
            disableContextualMode();
        }
    }

    @Override
    public HashMap<String, Contact> getLocalContacts() {
        return contactsData;
    }
}