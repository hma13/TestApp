//package com.example.mytest.ui;
//
//import androidx.test.rule.ActivityTestRule;
//
//import com.example.mytest.CommitListActivityViewModel;
//import com.example.mytest.SingleFragmentActivity;
//import com.example.mytest.util.ViewModelUtil;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//
//import static org.mockito.Mockito.mock;
//
//public class CommitListFragmentTest {
//    @Rule
//    public ActivityTestRule<SingleFragmentActivity> activityTestRule =
//            new ActivityTestRule<>(SingleFragmentActivity.class, true, true);
//    @Rule
//    public TaskExecutorWithIdlingResourceRule executorRule =
//            new TaskExecutorWithIdlingResourceRule();
//
//    private CommitListActivityViewModel viewModel;
//
//    @Before
//    public void setup() {
//        viewModel = mock(CommitListActivityViewModel.class);
//        AddEventFragment addEventFragment = new AddEventFragment();
//
//        addEventFragment.countdownViewModelFactory = ViewModelUtil.createFor(viewModel);
//        activityTestRule.getActivity().setFragment(addEventFragment);
//    }
//
//
//    @Test
//    public void addEvent() {
//    }
//}
