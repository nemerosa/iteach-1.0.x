package net.nemerosa.iteach.service;

import net.nemerosa.iteach.common.Ack;
import net.nemerosa.iteach.common.ID;
import net.nemerosa.iteach.service.model.*;

import java.util.Locale;
import java.util.stream.Stream;

public interface AccountService {

    ID register(Locale locale, TeacherRegistrationForm form);

    Ack completeRegistration(Locale locale, String token);

    Account getAccount(int id);

    Stream<Account> getAccounts();

    Ack deleteAccount(int accountId);

    Profile getProfile();

    void saveProfile(Profile profile);

    Setup getSetup();

    Ack saveSetup(SetupForm form);
}
