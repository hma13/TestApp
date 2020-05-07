package com.example.github.ui.commit;

import androidx.core.util.Pair;
import androidx.lifecycle.MutableLiveData;
import androidx.test.rule.ActivityTestRule;

import com.example.github.R;
import com.example.github.SingleFragmentActivity;
import com.example.github.TaskExecutorWithIdlingResourceRule;
import com.example.github.data.CommitListItem;
import com.example.github.util.ViewModelUtil;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    public ActivityTestRule<SingleFragmentActivity> activityTestRule =
            new ActivityTestRule<>(SingleFragmentActivity.class, true, true);
    @Rule
    public TaskExecutorWithIdlingResourceRule executorRule = new TaskExecutorWithIdlingResourceRule();

    private CommitListFragmentViewModel mockViewModel;
    private CommitListFragment listFragment;

    @Before
    public void setup() {
        mockViewModel = mock(CommitListFragmentViewModel.class);
        listFragment = new CommitListFragment();
        listFragment.viewModelFactory = ViewModelUtil.createFor(mockViewModel);
    }


    @Test
    public void testSuccess() {
        List<CommitListItem> list = new ArrayList<>();
        CommitListItem sha = new CommitListItem("sha");
        list.add(sha);
        MutableLiveData<Pair<List<CommitListItem>, Throwable>> liveData = new MutableLiveData<>();
        liveData.postValue(Pair.create(list, null));
        when(mockViewModel.getCommitsLiveData()).thenReturn(liveData);
        MutableLiveData<Boolean> fetchingLiveData = new MutableLiveData<>();
        fetchingLiveData.postValue(false);
        when(mockViewModel.getFetchingLiveData()).thenReturn(fetchingLiveData);
        activityTestRule.getActivity().setFragment(listFragment);

        onView(allOf(withText("sha"), withId(R.id.hash))).check(matches(isDisplayed()));

    }

    public static Matcher withSha(Matcher<CommitListItem> nameMatcher) {
        return new TypeSafeMatcher<CommitListItem>() {
            @Override
            public void describeTo(Description description) {

            }

            @Override
            public boolean matchesSafely(CommitListItem commit) {
                return nameMatcher.matches(commit.getSha());
            }

        };
    }
}
