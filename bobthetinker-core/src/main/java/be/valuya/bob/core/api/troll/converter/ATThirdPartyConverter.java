package be.valuya.bob.core.api.troll.converter;

import be.valuya.accountingtroll.domain.ATThirdParty;
import be.valuya.accountingtroll.domain.ATThirdPartyType;
import be.valuya.bob.core.domain.BobCompany;

import java.util.Optional;
import java.util.stream.Stream;

public class ATThirdPartyConverter {

    public Stream<ATThirdParty> convertToTrollThirdParties(BobCompany bobCompany) {
        // A single company for both client/suppliers, while a thirdparty has a single purpose
        Optional<String> cVatNumberOptional = bobCompany.getcVatNumberOptional();
        Optional<String> sVatNumberOptional = bobCompany.getsVatNumberOptional();

        ATThirdParty clientThirdParty = createBaseThirdParty(bobCompany);
        clientThirdParty.setType(ATThirdPartyType.CLIENT);
        cVatNumberOptional.ifPresent(clientThirdParty::setVatNumber);

        ATThirdParty supplierThirdParty = createBaseThirdParty(bobCompany);
        supplierThirdParty.setType(ATThirdPartyType.SUPPLIER);
        sVatNumberOptional.ifPresent(supplierThirdParty::setVatNumber);

        return Stream.of(clientThirdParty, supplierThirdParty);
    }


    private ATThirdParty createBaseThirdParty(BobCompany bobCompany) {
        String id = bobCompany.getcId();
        String fullName = bobCompany.getcName1();
        Optional<String> address = bobCompany.getcAddress1Optional().filter(this::isNotEmptyString);
        Optional<String> zipCode = bobCompany.getcZipCodeOptional().filter(this::isNotEmptyString);
        Optional<String> city = bobCompany.getcLocalityOptional().filter(this::isNotEmptyString);
        Optional<String> countryCode = bobCompany.getcCountryCodeOptional().filter(this::isNotEmptyString);
        Optional<String> phoneNumber = bobCompany.getcPhoneNumberOptional().filter(this::isNotEmptyString);
        Optional<String> bankAccount = bobCompany.getcBankNumberOptional().filter(this::isNotEmptyString);
        Optional<String> languageOptional = bobCompany.getcLanguageOptional().filter(this::isNotEmptyString);

        ATThirdParty baseThirdParty = new ATThirdParty();
        baseThirdParty.setId(id);
        baseThirdParty.setFullName(fullName);
        baseThirdParty.setAddress(address.orElse(null));
        baseThirdParty.setZipCode(zipCode.orElse(null));
        baseThirdParty.setCity(city.orElse(null));
        baseThirdParty.setCountryCode(countryCode.orElse(null));
        baseThirdParty.setPhoneNumber(phoneNumber.orElse(null));
        baseThirdParty.setBankAccountNumber(bankAccount.orElse(null));
        baseThirdParty.setLanguage(languageOptional.orElse(null));
        return baseThirdParty;
    }

    private boolean isNotEmptyString(String s) {
        return !s.trim().isEmpty();
    }

}
