package com.clackjones.cymraeg.user;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;
import org.mockito.MockitoAnnotations;

public class UserManagerTest {
    @Mock
    private UserDao userDao;

    @Mock
    private UserEntityToUserMapper entityToUserMapper;

    @InjectMocks
    private UserManager userManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnNullWhenUserNotFound() {
        // given
        given(userDao.findById("user12345")).willReturn(null);
        given(entityToUserMapper.map(any())).willReturn(new User());

        // then
        assertThat(userManager.findUserByUsername("user12345"), nullValue());
        then(userDao).should(times(1)).findById("user12345");
        then(entityToUserMapper).should(never()).map(any());
    }

    @Test
    public void shouldReturnUserObjectWhenUserFound() {
        // given
        given(userDao.findById("user12345")).willReturn(new UserEntity());
        given(entityToUserMapper.map(any())).willReturn(new User());

        // when
        User result = userManager.findUserByUsername("user12345");

        // then
        assertThat(result, notNullValue());
        then(userDao).should(times(1)).findById("user12345");
        then(entityToUserMapper).should(times(1)).map(any());
    }
}
