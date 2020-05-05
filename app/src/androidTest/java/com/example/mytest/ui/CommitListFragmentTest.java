package com.example.mytest.ui;

import androidx.core.util.Pair;
import androidx.lifecycle.MutableLiveData;
import androidx.test.rule.ActivityTestRule;

import com.example.mytest.SingleFragmentActivity;
import com.example.mytest.data.Commit;
import com.example.mytest.util.ViewModelUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommitListFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityTestRule =
            new ActivityTestRule<>(SingleFragmentActivity.class, true, true);
    @Rule
    public TaskExecutorWithIdlingResourceRule executorRule = new TaskExecutorWithIdlingResourceRule();

    private CommitListFragmentViewModel viewModel;

    @Before
    public void setup() {
        viewModel = mock(CommitListFragmentViewModel.class);
        CommitListFragment listFragment = new CommitListFragment();

        listFragment.viewModelFactory = ViewModelUtil.createFor(viewModel);
        activityTestRule.getActivity().setFragment(listFragment);
    }


    @Test
    public void testSuccess() {
        ArrayList<Commit> list = new ArrayList<>();
        list.add(new Commit("sha"));
        MutableLiveData<Pair<List<Commit>, Throwable>> liveData = new MutableLiveData<>();
        liveData.setValue(Pair.create(list, null));
        when(viewModel.getCommitsLiveData()).thenReturn(liveData);

        onView(withText("sha")).check(matches(isDisplayed()));
    }
}
