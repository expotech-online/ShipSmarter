package org.ahmedukamel.shipsmarter.model;

import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PhoneNumber {
    private Integer countryCode;

    private Long nationalNumber;

    public PhoneNumber(Phonenumber.PhoneNumber phoneNumber) {
        this.countryCode = phoneNumber.getCountryCode();
        this.nationalNumber = phoneNumber.getNationalNumber();
    }

    @Override
    public String toString() {
        return (countryCode == null || nationalNumber == null) ? null :
                "+%d%d".formatted(this.countryCode, this.nationalNumber);
    }
}