package com.example.mytest.ui;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.mytest.RxImmediateSchedulerRule;
import com.example.mytest.data.Commit;
import com.example.mytest.repo.DataRepo;

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

public class CommitListFragmentViewModelTest {
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
        ArrayList<Commit> list = new ArrayList<>();
        list.add(new Commit());
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