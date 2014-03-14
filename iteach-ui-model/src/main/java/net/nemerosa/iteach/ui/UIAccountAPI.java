package net.nemerosa.iteach.ui;

import net.nemerosa.iteach.common.Ack;
import net.nemerosa.iteach.common.ID;
import net.nemerosa.iteach.common.TokenType;
import net.nemerosa.iteach.ui.model.UIAccount;
import net.nemerosa.iteach.ui.model.UIState;
import net.nemerosa.iteach.ui.model.UITeacher;
import net.nemerosa.iteach.ui.model.UITeacherPasswordForm;

import java.util.List;
import java.util.Locale;

public interface UIAccountAPI {

    UIState state(Locale locale);

    UITeacher login(Locale locale);

    // TODO Resource collection
    List<UIAccount> getAccounts(Locale locale);

    ID registerAsTeacherWithPassword(Locale locale, UITeacherPasswordForm form);

    Ack validate(Locale locale, TokenType tokenType, String token);
}