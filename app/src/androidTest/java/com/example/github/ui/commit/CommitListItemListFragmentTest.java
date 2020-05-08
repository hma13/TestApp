package com.example.github.ui.commit;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.lifecycle.MutableLiveData;

import com.example.github.R;
import com.example.github.data.CommitListItem;
import com.example.github.util.TaskExecutorWithIdlingResourceRule;
import com.example.github.util.ViewModelUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.testing.FragmentScenario.launchInContainer;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommitListItemListFragmentTest {
    @Rule
    public TaskExecutorWithIdlingResourceRule executorRule = new TaskExecutorWithIdlingResourceRule();

    private CommitListFragmentViewModel mockViewModel;
    private MutableLiveData<Pair<List<CommitListItem>, Throwable>> commitsLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> fetchingLiveData = new MutableLiveData<>();
    private FragmentFactory fragmentFactory = new FragmentFactory() {
        @NonNull
        @Override
        public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
            CommitListFragment fragment = new CommitListFragment();
            fragment.viewModelFactory = ViewModelUtil.createFor(mockViewModel);
            return fragment;
        }
    };

    @Before
    public void setup() {
        mockViewModel = mock(CommitListFragmentViewModel.class);
        when(mockViewModel.getCommitsLiveData()).thenReturn(commitsLiveData);
        when(mockViewModel.getFetchingLiveData()).thenReturn(fetchingLiveData);
        launchInContainer(CommitListFragment.class, null, fragmentFactory);
    }

    @Test
    public void testSuccess() {
        List<CommitListItem> list = new ArrayList<>();
        CommitListItem sha = new CommitListItem("sha");
        list.add(sha);
        commitsLiveData.postValue(Pair.create(list, null));
        onView(allOf(withText("sha"), withId(R.id.hash))).check(matches(isDisplayed()));
    }
}
