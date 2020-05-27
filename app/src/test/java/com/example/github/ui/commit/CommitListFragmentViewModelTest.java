package com.example.github.ui.commit;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.core.util.Pair;
import androidx.lifecycle.Observer;

import com.example.github.db.CommitListItemEntity;
import com.example.github.repository.CommitRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommitListFragmentViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private CommitRepository mockRepo;

    @Mock
    private Observer<Pair<List<CommitListItemEntity>, Throwable>> mockObserver;

    private CommitListFragmentViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = new CommitListFragmentViewModel(mockRepo);
        viewModel.getCommitsLiveData().observeForever(mockObserver);
    }

    @After
    public void teardown() {
    }

    @Test
    public void testSuccess() {
        ArrayList<CommitListItemEntity> list = new ArrayList<>();
        list.add(new CommitListItemEntity());
        when(mockRepo.getCommits(anyString(), anyString(), nullable(String.class), anyBoolean())).thenReturn(Single.just(list));
        viewModel.fetchCommits();
        verify(mockObserver).onChanged(Pair.create(list, null));
    }

    @Test
    public void testException() {
        RuntimeException error = new RuntimeException("error");
        when(mockRepo.getCommits(anyString(), anyString(), nullable(String.class), anyBoolean())).thenReturn(Single.error(error));
        viewModel.fetchCommits();
        verify(mockObserver).onChanged(Pair.create(null, error));
    }
}
