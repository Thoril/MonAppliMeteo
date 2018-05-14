package com.example.artru.monapplimeteo.task;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by artru on 14/05/2018.
 */

public class LastSearchProvider extends SearchRecentSuggestionsProvider{
    public final static String AUTHORITY = "com.example.artru.monapplimeteo.task.lastSearchProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;
    public LastSearchProvider() {
        super();
        setupSuggestions(AUTHORITY,MODE);

    }
}
