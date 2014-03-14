package net.nemerosa.iteach.ui;

import net.nemerosa.iteach.ui.model.UIForm;
import net.nemerosa.iteach.ui.model.UISchool;
import net.nemerosa.iteach.ui.model.UISchoolCollection;
import net.nemerosa.iteach.ui.model.form.UIFormDefinition;

import java.util.Locale;

/**
 * UI for the teacher.
 */
public interface UITeacherAPI {

    /**
     * Gets the list of schools for a teacher
     */
    UISchoolCollection getSchools(Locale locale);

    /**
     * Gets the form for a school
     */
    UIFormDefinition getSchoolForm(Locale locale);

    /**
     * Creates a school
     */
    UISchool createSchool(Locale locale, UIForm form);

    /**
     * Gets a school
     */
    UISchool getSchool(Locale locale, int schoolId);

}