package com.example.github.repository;

import com.example.github.api.GithubApiService;
import com.example.github.util.InstantAppSchedulers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommitRepositoryTest {

    @Mock
    private GithubApiService apiService;
    private CommitRepository commitRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        commitRepository = new CommitRepository(apiService, new InstantAppSchedulers());
    }

    @Test
    public void testGetCommits() {
        when(apiService.getCommits(anyString(), anyString(), nullable(String.class))).thenReturn(Single.just(new ArrayList<>()));
        commitRepository.getCommits("owner", "test", null);
        verify(apiService).getCommits("owner", "test", null);
    }
}
