package be.valuya.bob.core.api.troll.converter;

import be.valuya.accountingtroll.domain.ATAccount;
import be.valuya.accountingtroll.domain.ATAccountImputationType;
import be.valuya.bob.core.domain.BobAccount;

public class ATAccountConverter {


    public ATAccount convertToTrollAccount(BobAccount bobAccount) {
        String accountNumber = bobAccount.getAid();
        String name = bobAccount.getHeading1Optional()
                .orElse("-");
        boolean yearResetAccount = isYearResetAccount(accountNumber);
        String adbcd = bobAccount.getAdbcd();
        ATAccountImputationType atAccountImputationType = this.createATAccountImputationType(adbcd);

        ATAccount account = new ATAccount();
        account.setCode(accountNumber);
        account.setName(name);
        account.setYearlyBalanceReset(yearResetAccount);
        account.setImputationType(atAccountImputationType);
        return account;
    }


    private boolean isYearResetAccount(String accountCode) {
        return accountCode.startsWith("6") || accountCode.startsWith("7");
    }

    private ATAccountImputationType createATAccountImputationType(String value) {
        switch (value) {
            case "D":
                return ATAccountImputationType.DEBIT;
            case "C":
                return ATAccountImputationType.CREDIT;
            default:
                return ATAccountImputationType.UNKNOWN;
        }
    }

}
