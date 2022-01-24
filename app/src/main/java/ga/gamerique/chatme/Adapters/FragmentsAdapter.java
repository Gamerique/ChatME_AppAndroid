package ga.gamerique.chatme.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ga.gamerique.chatme.Fragments.Chats;
import ga.gamerique.chatme.Fragments.GlobalChats;
import ga.gamerique.chatme.Fragments.RandomChats;

public class FragmentsAdapter extends FragmentPagerAdapter {
    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Chats();
            case 1: return new GlobalChats();
            case 2: return new RandomChats();
            default: return new Chats();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0){
            title = "CHATS";
        }
        if (position == 1){
            title = "GLOBAL CHATS";
        }
        if (position == 2){
            title = "RANDOM CHATS";
        }

        return title;
    }
}
