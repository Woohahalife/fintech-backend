package com.core.backend.account.domain;

import com.core.backend.account.exception.AccountException;
import com.core.backend.common.exception.ErrorCode;
import com.core.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank_code", nullable = false)
    private BankCode bankCode;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "main_account", nullable = false)
    private boolean mainAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Account(String bankName, String accountNumber, User user) {
        this.bankCode = BankCode.valueOfBankName(bankName);
        this.balance = 10000000L; // 일괄적으로 1000만원 보유 (dummy data)
        this.accountNumber = accountNumber;
        this.mainAccount = false;
        this.user = user;
    }

    public static Account of(String bankName, String accountNumber, User user) {
        return new Account(bankName, accountNumber, user);
    }

    public void setMainAccount(boolean mark) {
        this.mainAccount = mark;
    }

    public boolean validateSufficientBalance(Long amount) {
        return this.balance < amount;
    }

    public void decreaseBalance(Long amount) {
        if (amount == null || amount < 10000) { // TODO : 최소 환전 금액 기준 세우기(일단 1만원으로 설정함)
            throw new AccountException(ErrorCode.ERROR_AMOUNT_TOO_LOW);
        }

        if(validateSufficientBalance(amount)) {
            throw new AccountException(ErrorCode.INSUFFICIENT_BALANCE);
        }

        this.balance -= amount;
    }
}
