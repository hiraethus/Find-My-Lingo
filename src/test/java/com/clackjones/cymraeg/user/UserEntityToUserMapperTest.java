package com.clackjones.cymraeg.user;

import com.clackjones.cymraeg.gwasanaeth.Gwasanaeth;
import com.clackjones.cymraeg.gwasanaeth.GwasanaethEntity;
import com.clackjones.cymraeg.gwasanaeth.GwasanaethEntityToGwasanaethMapper;
import org.junit.*;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

public class UserEntityToUserMapperTest {

    @Mock
    private GwasanaethEntityToGwasanaethMapper entityToGwasanaethMapper;

    @InjectMocks
    private UserEntityToUserMapper entityToUserMapper;


    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        given(entityToGwasanaethMapper.map(any())).willReturn(new Gwasanaeth());
    }

    @Test
    public void shouldMapEntityToUserObject() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("User123");
        userEntity.setPassword("pass789021");
        userEntity.setNickname("henry");

        List<GwasanaethEntity> gwasanaethEntities = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            gwasanaethEntities.add(new GwasanaethEntity());
        }
        userEntity.setGwasanaethau(gwasanaethEntities);

        // when
        User result = entityToUserMapper.map(userEntity);

        // then
        assertThat(result.getUsername(), equalTo("User123"));
        assertThat(result.getGwasanaethau(), notNullValue());
        assertThat(result.getGwasanaethau().size(), equalTo(3));
        assertThat(result.getNickname(), equalTo("henry"));

        then(entityToGwasanaethMapper).should(times(3)).map(any());
    }

    @Test
    public void shouldReturnNullIfNull() {
        assertThat(entityToUserMapper.map(null), nullValue());
    }
}
