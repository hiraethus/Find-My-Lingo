package com.clackjones.cymraeg.user;

import com.clackjones.cymraeg.gwasanaeth.Gwasanaeth;
import com.clackjones.cymraeg.gwasanaeth.GwasanaethEntityToGwasanaethMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
class UserEntityToUserMapper {

    @Autowired
    private GwasanaethEntityToGwasanaethMapper entityToGwasanaethMapper;

    User map(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        User user = new User();

        if (userEntity.getUsername() != null) user.setUsername(userEntity.getUsername());

        user.setGwasanaethau(mapGwasanaethau(userEntity));

        return user;
    }

    private Collection<Gwasanaeth> mapGwasanaethau(UserEntity userEntity) {
        if (userEntity.getGwasanaethau() != null) {
            List<Gwasanaeth> gwasanaethau = userEntity.getGwasanaethau().stream()
                    .map(entity -> entityToGwasanaethMapper.map(entity))
                    .collect(Collectors.toList());

            return gwasanaethau;
        }

        return null;
    }
}
