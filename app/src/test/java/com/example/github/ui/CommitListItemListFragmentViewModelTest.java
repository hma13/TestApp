package com.example.github.ui;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.github.RxImmediateSchedulerRule;
import com.example.github.data.CommitListItem;
import com.example.github.repo.DataRepo;
import com.example.github.ui.commit.CommitListFragmentViewModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

public class CommitListItemListFragmentViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Rule
    public RxImmediateSchedulerRule rxImmediateSchedulerRule = new RxImmediateSchedulerRule();


    @Mock
    DataRepo mockRepo;

    private CommitListFragmentViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = new CommitListFragmentViewModel(mockRepo);
    }

    @After
    public void teardown() {
    }

    @Test
    public void testSuccess() {
        ArrayList<CommitListItem> list = new ArrayList<>();
        list.add(new CommitListItem());
        when(mockRepo.getCommits(anyString(), anyString(), nullable(String.class))).thenReturn(Single.just(list));
        viewModel.fetchCommits();
        Assert.assertEquals(1, viewModel.getCommitsLiveData().getValue().first.size());
    }

    @Test
    public void testException() {
        when(mockRepo.getCommits(anyString(), anyString(), nullable(String.class))).thenReturn(Single.error(new RuntimeException("error")));
        viewModel.fetchCommits();
        Assert.assertNotNull(viewModel.getCommitsLiveData().getValue().second);
    }
}
