package com.clackjones.cymraeg.user;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@NamedQueries({
        @NamedQuery(name = "PasswordResetTokenEntity.findByEmailAndToken",
                query = "SELECT p FROM PasswordResetTokenEntity p WHERE p.token = :token AND p.user = :user")
})
class PasswordResetTokenEntity {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "username")
    private UserEntity user;

    @Column(name="CREATION_TS", insertable=false, updatable=false)
    private Calendar dateCreated;

    public PasswordResetTokenEntity() {}

    public PasswordResetTokenEntity(String token, UserEntity user) {
        this.token = token;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public UserEntity getUser() {
        return user;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }
}
