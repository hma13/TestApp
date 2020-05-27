package com.example.github.repository;

import com.example.github.api.GithubApiService;
import com.example.github.db.CommitListItemEntityDao;
import com.example.github.util.InstantAppSchedulers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.Response;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommitRepositoryTest {

    @Mock
    private GithubApiService mockApi;
    @Mock
    private CommitListItemEntityDao mockDao;
    private CommitRepository commitRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        commitRepository = new CommitRepository(mockApi, new InstantAppSchedulers(), mockDao);
    }

    @Test
    public void testGetCommits() {
        when(mockApi.getCommits(anyString(), anyString(), nullable(String.class))).thenReturn(Single.just(Response.success(new ArrayList<>())));
        commitRepository.getCommits("owner", "test", null, false);
        verify(mockApi).getCommits("owner", "test", null);
    }
}
