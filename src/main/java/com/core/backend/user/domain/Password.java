package com.core.backend.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    @Column(nullable = false)
    private String password;



    protected Password(String password) {
        this.password = password;
    }

    public Password encodePassword(String password, PasswordEncryptor encoder) {
        this.password = encoder.encrypt(password);
        return this;
    }

    public String decode(PasswordEncryptor decoder) {

        return decoder.decrypt(this.password);
    }
}
