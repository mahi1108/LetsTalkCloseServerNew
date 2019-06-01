package com.mLabsCloseServer.LetsTalk.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001b"}, d2 = {"Lcom/mLabsCloseServer/LetsTalk/adapters/UsersAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/mLabsCloseServer/LetsTalk/adapters/UsersHolder;", "mainActivity", "Lcom/mLabsCloseServer/LetsTalk/activities/MainActivity;", "users_data", "Ljava/util/ArrayList;", "Lcom/mLabsCloseServer/LetsTalk/models/UserInfo;", "(Lcom/mLabsCloseServer/LetsTalk/activities/MainActivity;Ljava/util/ArrayList;)V", "getMainActivity", "()Lcom/mLabsCloseServer/LetsTalk/activities/MainActivity;", "setMainActivity", "(Lcom/mLabsCloseServer/LetsTalk/activities/MainActivity;)V", "getUsers_data", "()Ljava/util/ArrayList;", "setUsers_data", "(Ljava/util/ArrayList;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_debug"})
public final class UsersAdapter extends android.support.v7.widget.RecyclerView.Adapter<com.mLabsCloseServer.LetsTalk.adapters.UsersHolder> {
    @org.jetbrains.annotations.NotNull()
    private com.mLabsCloseServer.LetsTalk.activities.MainActivity mainActivity;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.mLabsCloseServer.LetsTalk.models.UserInfo> users_data;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.mLabsCloseServer.LetsTalk.adapters.UsersHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.mLabsCloseServer.LetsTalk.adapters.UsersHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.mLabsCloseServer.LetsTalk.activities.MainActivity getMainActivity() {
        return null;
    }
    
    public final void setMainActivity(@org.jetbrains.annotations.NotNull()
    com.mLabsCloseServer.LetsTalk.activities.MainActivity p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.mLabsCloseServer.LetsTalk.models.UserInfo> getUsers_data() {
        return null;
    }
    
    public final void setUsers_data(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.mLabsCloseServer.LetsTalk.models.UserInfo> p0) {
    }
    
    public UsersAdapter(@org.jetbrains.annotations.NotNull()
    com.mLabsCloseServer.LetsTalk.activities.MainActivity mainActivity, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.mLabsCloseServer.LetsTalk.models.UserInfo> users_data) {
        super();
    }
}