package com.example.lucamele.journey.model;

import android.support.v4.app.Fragment;

public interface ReplaceableFragment
{

    static Fragment getNewInstance() {
        return new Fragment();
    }
}