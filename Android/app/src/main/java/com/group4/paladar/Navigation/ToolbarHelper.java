package com.group4.paladar.Navigation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.group4.paladar.FireBaseStructures.user.UserFirebase;
import com.group4.paladar.MainActivity;
import com.group4.paladar.R;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;

/**
 * Created by Alex on 2016-02-24.
 * This class should handle Toolbar , Navigation drawer and Floating click icon clicks and setup phase.
 *  this uses the Material drawer tools alot!
 *  https://github.com/mikepenz/MaterialDrawer
 */
public class ToolbarHelper implements Drawer.OnDrawerItemClickListener, AccountHeader.OnAccountHeaderListener {

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    private static final int PROFILE_SETTING = 100000;


    /* Navigation Items. */

    //primary stuff
    PrimaryDrawerItem item_search;
    PrimaryDrawerItem item_filter;
    PrimaryDrawerItem item_reservation;
    //chef options
    PrimaryDrawerItem item_createHome;
    PrimaryDrawerItem item_createEvent;
    //account stuff
    PrimaryDrawerItem item_settings;
    PrimaryDrawerItem item_sign;

    //divders with title
    SectionDrawerItem sectionChef;
    //

    //user profile
    private IProfile profile;


    /* Interfacing creating callbacks for main acitivity!
     * http://stackoverflow.com/questions/3398363/how-to-define-callbacks-in-android
     * */
    private onNavigationItemClicked handler;

    public Drawer getNavigationDrawer() {
        return result;
    }


    public interface onNavigationItemClicked {
        void onSearch();
        void onFilter();
        void onReservation();
        void onCreateHome();
        void onCreateEvent();
        void onSettings();
        void onSign();
    }

    public void setOnNavigationItemListener(onNavigationItemClicked listener) {
        handler = listener;
    }


    public void onCreate(MainActivity mActivity, Bundle savedInstanceState) {

        setupImageHandler(mActivity);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) mActivity.findViewById(R.id.toolbar);
        mActivity.setSupportActionBar(toolbar);

        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        //final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon("https://avatars3.githubusercontent.com/u/1476232?v=3&s=460").withIdentifier(100);


        // Create the Account/drawer Header
        headerResult = new AccountHeaderBuilder()
                .withActivity(mActivity)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.side_nav_bar)
                .withSavedInstance(savedInstanceState)
                .withAlternativeProfileHeaderSwitching(false)
                .withSelectionListEnabled(false)
                .build();


        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(mActivity)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .withOnDrawerItemClickListener(this)
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();

        //generates navigation items
        setupNavigationItems();

        //only set the active selection or active profile if we do not recreate the activity
        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 11
            result.setSelection(21, false);

            //set the active profile
            //headerResult.setActiveProfile(profile3);
        }
    }

    /**
     * setups image loading via url so we only need the url to load profile image.
     * @param mActivity
     */
    private void setupImageHandler(MainActivity mActivity) {

        //initialize and create the image loader logic
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Glide.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Glide.clear(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx, String tag) {
                //define different placeholders for different imageView targets
                //default tags are accessible via the DrawerImageLoader.Tags
                //custom ones can be checked via string. see the CustomUrlBasePrimaryDrawerItem LINE 111
                if (DrawerImageLoader.Tags.PROFILE.name().equals(tag)) {
                    return DrawerUIUtils.getPlaceHolder(ctx);
                } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name().equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(com.mikepenz.materialdrawer.R.color.primary).sizeDp(56);
                } else if ("customUrlItem".equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(R.color.md_red_500).sizeDp(56);
                }

                //we use the default one for
                //DrawerImageLoader.Tags.PROFILE_DRAWER_ITEM.name()
                return super.placeholder(ctx, tag);
            }
        });

    }



    public void SetUserProfile(UserFirebase userObject) {
        //if existing user profile on remove them.
        headerResult.clear();
        profile = new ProfileDrawerItem().withName(userObject.getDisplayName()).withEmail(userObject.getEmail()).withIcon(userObject.getProfileImageUrl()).withIdentifier(100);
        headerResult.addProfile(profile, 0);
    }

    public void removeUserProfile() {
        //remove all user profiles
        headerResult.clear();
    }

    //create navigation drawers items! "links to different views"
    private void setupNavigationItems() {

        //Generate navigation items
        SectionDrawerItem sectionSearch =  new SectionDrawerItem().withName(R.string.drawer_section_paladar).withDivider(false).withIdentifier(20);

        item_search = new PrimaryDrawerItem().withName(R.string.drawer_item_search).withIcon(GoogleMaterial.Icon.gmd_search).withIdentifier(1);
        item_filter = new PrimaryDrawerItem().withName(R.string.drawer_item_filter).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2).withSelectable(false);
        item_reservation = new PrimaryDrawerItem().withName(R.string.drawer_item_reservation).withIcon(GoogleMaterial.Icon.gmd_calendar_note).withIdentifier(3);
        //chef options
        sectionChef =  new SectionDrawerItem().withName(R.string.drawer_section_ChefOptions).withIdentifier(21);
        item_createEvent = new PrimaryDrawerItem().withName(R.string.drawer_item_createEvent).withIcon(GoogleMaterial.Icon.gmd_calendar).withIdentifier(5);
        //account stuff
        SectionDrawerItem sectionSettings =  new SectionDrawerItem().withName(R.string.drawer_section_userSettings).withIdentifier(22);;
        item_settings = new PrimaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(6);
        //creates sign in by default should change on successful sign in and sign out
        item_sign = new PrimaryDrawerItem().withName(R.string.drawer_item_sign_in).withIcon(GoogleMaterial.Icon.gmd_sign_in).withIdentifier(7).withSelectable(false);


        //add items to navigation drawer
        result.addItem(sectionSearch);
        result.addItem(item_search);
        result.addItem(item_filter);
        result.addItem(item_reservation);

     /* //ignore adding chef options
        result.addItem(sectionChef);
        result.addItem(item_createHome);
        result.addItem(item_createEvent); */
        result.addItem(sectionSettings);
        result.addItem(item_settings);
        result.addItem(item_sign);
    }

    public void enableChefOptions(){
        result.removeItems(21,  5);
        result.addItemsAtPosition(5, sectionChef,item_createEvent);
    }

    public void disableChefOptions(){
        result.removeItems(21,  5);
    }

    //this is called when Navigation drawer items are clicked
    //this also includes profile header!
    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

        if (drawerItem == null){
            //there are different reasons for the drawerItem to be null
            //--> click on the header
            //--> click on the footer
            //those items don't contain a drawerItem
        }

        //handles navigation drawer ite clicked
        if (drawerItem != null){
          final long id = drawerItem.getIdentifier();

            if (id == 1){
                //search button has been clicked!
                handler.onSearch();
            } else if (id == 2){
                // filter button has been clicked;
                handler.onFilter();
            } else if (id == 3){
                //reservation button clicked
                handler.onReservation();
            } else if (id == 4){
                //create Home button clicked
                handler.onCreateHome();
            } else if (id == 5){
                //create event button clicked
                handler.onCreateEvent();
            } else if (id == 6){
                //settings button clicked
                handler.onSettings();
            } else if (id == 7){
                //sign in / sign out button clicked
                handler.onSign();
               // result.closeDrawer();
            }
        }

        return false;
    }

    /**
     * Updates sign navigation item to say sign in
     */
    public void setSignIn() {
        item_sign.withName("Sign In");
        result.updateItem(item_sign);
    }

    /**
     * Updates sign navigation item to say sign out
     */
    public void setSignOut() {
        item_sign.withName("Sign out");
        result.updateItem(item_sign);
    }

    //account clicked or changed
    //maybe link to settings?
    @Override
    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
        //TODO add link to settings
        return false;
    }



}
