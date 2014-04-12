var map_en = {
    // General
    'language.en': 'English',
    'language.fr': 'Français',
    'yes': "Yes",
    'no': "No",
    'calendar.dateFormat': 'EEE MMMM dd yyyy',
    'dialog.ok': 'OK',
    'dialog.cancel': 'Cancel',
    'dialog.update': "Modify",
    'dialog.delete': "Delete",
    'dialog.save': "Save",
    'logout': "Sign out",
    // Home page
    'home.back': "Close",
    // Error page
    'error': "Error",
    'error.message': "The page you have requested cannot be displayed.",
    // Login page
    'login.iteach.message': 'iTeach is the application that will help you organise your courses and your planning.',
    'login.signin.google': 'Sign in with Google',
    'login.signin.with': 'Sign in with',
    'login.signin.form': 'Sign in with iTeach',
    'login.signin.form.email': 'Enter your email or your identifier',
    'login.signin.form.password': 'Enter your password',
    'login.signin.form.submit': 'Sign in',
    'login.signin.form.register': 'Register',
    'login.error': "Cannot connect to iTeach. Check your credentials.",
    'login.error.openid_registration_non_valid': "Cannot connect to iTeach. Your account has not been verified yet or has been disabled.",
    'login.error.openid_failed': "Cannot connect to iTeach. Your OpenID cannot be verified or accessed.",
    // Register page
    'register.page': "Registration",
    'register.name': 'Your name',
    'register.email': 'Your email',
    'register.password': 'Create a password',
    'register.password_confirm': 'Confirm the password',
    'register.submit': 'Submit',
    'register.cancel': 'Cancel',
    'register.incorrect_password_confirmation': 'The confirmation of the password is not correct',
    'register.success': "The registration has been accepted. You will now shortly receive a mail that will contain " +
        "a link. Follow the instructions in this mail in order to complete your registration.",
    'register.result.ko': "The registration could not be completed. Please contact the iTeach team for more information.",
    'register.result.ok': "The registration is now complete. Please proceed with the login to start using iTeach",
    'register.result.login': "Go on with login",
    // Teacher
    'teacher.planning': "My planning",
    'teacher.schools': "My schools",
    'teacher.newSchool': "New school",
    'teacher.students': "My students",
    'teacher.newStudent': "New student",
    // School
    'school': 'School',
    'school.name': "Name",
    'school.name.error': "Name is required.",
    'school.colour': "Colour",
    'school.colour.error': "Selection of the colour is not correct.",
    'school.contact': "Contact",
    'school.hourlyRate': "Hourly rate",
    'school.hourlyRate.help': 'Invoicing amount per hour (VAT excl.). For example, EUR 45.00 or 45.00 (defaults to EUR)',
    'school.hourlyRate.error.format': "The hourly rate must look like EUR 45.00 or 45.00.",
    'school.phone': 'Phone',
    'school.mobilePhone': 'Mobile phone',
    'school.postalAddress': 'Postal address',
    'school.webSite': 'Web site',
    'school.webSite.error': "The web site URL is not valid.",
    'school.vat': "VAT number",
    'school.vat.help': "VAT number used to generated invoices for this school",
    'school.vatRate': "VAT rate (%)",
    'school.vatRate.help': "VAT rate to apply for this school (leave blank if VAT non applicable)",
    'school.vatRate.error': "The VAT rate must be a valid decimal number.",
    'school.email': 'Email',
    'school.email.error': "Email is not valid.",
    'school.delete.prompt': "Do you want really to delete this school? The associated students and lessons will be lost.",
    'school.students': "Its students",
    // Student
    'student': "Student",
    'student.school': "School",
    'student.name': "Name",
    'student.name.error': "Name is required",
    'student.subject': "Subject",
    'student.phone': 'Phone',
    'student.mobilePhone': 'Mobile phone',
    'student.postalAddress': 'Postal address',
    'student.email': 'Email',
    'student.email.error': "Email is not valid.",
    'student.disable': "Disable",
    'student.disable.message': "This student is currently disabled. You can re-enable it by clicking on the " +
        "'Enable' button below.",
    'student.enable': "Enable",
    'student.disable.prompt': "Disabling this student will hide him from the list of students and from the monthly " +
        "report, but he will remain visible in the list of students for the school and in the list of lessons. The " +
        "student can also be enabled again later.",
    'student.delete.prompt': "Do you want really to delete this student? The associated lessons will be lost.",
    'student.lessons': "His lessons",
    // Lesson
    'lesson': "Lesson",
    'lesson.tip': "Create a new lesson by selection a date, a period of time, a student and an optional location.",
    'lesson.student': "Student",
    'lesson.school': "School",
    'lesson.student.error': "A student must be selected.",
    'lesson.date': "Date",
    'lesson.from': "From",
    'lesson.to': "to",
    'lesson.location': "Location",
    'lesson.schedule': "Schedule",
    'lesson.delete.prompt': "Do you want really to delete this lesson? The associated comments will be lost.",
    // Comments
    'comments': "Comments",
    'comment.create': "Add comment",
    'comment.post': "Post comment",
    'comment.update': "Update comment",
    // Admin
    'admin.page': "Administration page",
    'admin.accounts': "Management of accounts",
    'admin.setup': "General set-up",
    'admin.setup.email': "Administrator email",
    'admin.setup.email.error': "Email is not valid.",
    'admin.setup.password': "Password",
    'admin.setup.password.help': "Enter your password in order to confirm your changes",
    'admin.setup.password.error': "Your password is required in order to save your changes.",
    'admin.setup.password.change': "Password change",
    'admin.setup.password.change.help': "Enter a new password (twice) if you want to change your password",
    'admin.setup.password.change.error': "The confirmation of your new password is not correct.",
    'admin.setup.saved': "Your setup changes have been saved.",
    'admin.setup.saved.passwordChanged': "Your setup changes have been saved. Your password change will be " +
        "taken into account at next login.",
    'admin.account.name': "Name",
    'admin.account.email': "Email",
    'admin.account.administrator': "Administrator",
    'admin.account.authenticationMode': "Authentication mode",
    'admin.account.authenticationMode.PASSWORD': "Password",
    'admin.account.authenticationMode.OPEN_ID': "Open ID",
    'admin.account.verified': "Verified",
    'admin.account.disabled': "Disabled",
    'admin.account.delete.prompt': "Do you want really to delete this account? All associated data will be lost.",
    'admin.account.import': "Import",
    'admin.account.import.title': "Import of data for {{name}}",
    'admin.account.import.message': "Select a JSON file previously exported from iTeach.",
    'admin.account.import.warning': "Warning! Importing data will erase any previous data for {{name}}.",
    'admin.account.import.success': "Import of data successful.",
    'admin.account.export': "Export",
    'admin.account.disable': "Disable",
    'admin.account.disable.message': "This account is currently disabled. You can re-enable it by clicking on the " +
        "'Enable' button below.",
    'admin.account.enable': "Enable",
    'admin.account.disable.prompt': "Disabling this account will prevent any connection. Do you want to continue?",
    // Profile
    'account.profile': "Profile",
    'account.profile.company': "Company",
    'account.profile.company.logo': "Company logo",
    'account.profile.company.logo.help': "Link to the company logo - must point to a valid image",
    'account.profile.postalAddress': "Address",
    'account.profile.phone': "Phone",
    'account.profile.vat': "VAT Number",
    'account.profile.vat.help': "VAT number of the company, used for the generation of invoices.",
    'account.profile.iban': "IBAN",
    'account.profile.bic': "BIC",
    // Account mgt
    'account.menu': 'Account',
    'account.passwordChange': 'Change password',
    'account.passwordChange.requested': "Your request for a password change has been accepted. You will shortly " +
        "receive a mail with a link that allows you to complete this change.",
    'account.passwordChange.old': "Old password",
    'account.passwordChange.new': "New password",
    'account.passwordChange.new.confirm': "Confirmation",
    'account.passwordChange.new.confirm.error': "Confirmation of the password is not correct.",
    'account.passwordChange.success': "Your password has been changed.",
    // Reporting
    'report.total': "Total",
    'report.monthly': "Monthly report",
    'report.hours.total': 'Total hours',
    'report.hours.period': "Hours for the month",
    'report.income.total': "Total income",
    // Invoicing
    'invoice.mgt': "Management of invoices",
    'invoice.generate': "Generate invoice",
    'invoice.month': "Year & Month",
    'invoice.number': "Invoice number",
    'invoice.generate.submit': "Generate",
    'invoice.generate.generating': "Generating the invoice...",
    'invoice.generate.list': "List of invoices",
    'invoice.generate.list.help': "You can access the list of invoices at any time in order to follow the progress " +
        "of the generation of the invoices.",
    'invoice.download': "Download invoice",
    'invoice.error.general': "An error occurred while generating the invoice. Please contact the support for more information",
    'invoice.error.uuid': "Error id to mention for support:"
};