angular.module('iteach.ui.teacher', [
        'iteach.config'
    ])
    .service('uiTeacher', function ($q, $http, config) {
        var self = {};

        /**
         * Schools
         */

        self.getSchools = function () {
            var d = $q.defer();
            $http.get(config.api('teacher/school'))
                .success(function (schools) {
                    d.resolve(schools);
                });
            return d.promise;
        };

        self.createSchool = function (school) {
            var d = $q.defer();
            $http.post(config.api('teacher/school'), school)
                .success(function (school) {
                    d.resolve(school);
                });
            return d.promise;
        };

        self.getSchool = function (schoolId) {
            return $http.get(config.api('teacher/school/' + schoolId));
        };

        self.updateSchool = function (schoolId, schoolForm) {
            var d = $q.defer();
            $http.put(config.api('teacher/school/' + schoolId), schoolForm)
                .success(function (school) {
                    d.resolve(school);
                });
            return d.promise;
        };

        self.deleteSchool = function (schoolId) {
            return $http.delete(config.api('teacher/school/' + schoolId));
        };

        /**
         * Students
         */

        self.getStudents = function (filtered) {
            return $http.get(config.api('teacher/student'), {
                params: {
                    filtered: filtered
                }
            });
        };

        self.createStudent = function (student) {
            var d = $q.defer();
            $http.post(config.api('teacher/student'), student)
                .success(function (student) {
                    d.resolve(student);
                });
            return d.promise;
        };

        self.getStudent = function (studentId) {
            return $http.get(config.api('teacher/student/' + studentId));
        };

        self.deleteStudent = function (studentId) {
            return $http.delete(config.api('teacher/student/' + studentId));
        };

        self.disableStudent = function (studentId) {
            return $http.put(config.api('teacher/student/{{id}}/disable', {id: studentId}));
        };

        self.enableStudent = function (studentId) {
            return $http.put(config.api('teacher/student/{{id}}/enable', {id: studentId}));
        };

        self.updateStudent = function (studentId, studentForm) {
            return $http.put(config.api('teacher/student/{{id}}', {id: studentId}), studentForm)
        };

        /**
         * Lessons
         */

        self.createLesson = function (lessonForm) {
            var d = $q.defer();
            $http.post(config.api('teacher/lesson'), lessonForm)
                .success(function (lesson) {
                    d.resolve(lesson);
                });
            return d.promise;
        };

        self.getLessons = function (filter) {
            var d = $q.defer();
            $http.post(config.api('teacher/lesson/filter'), filter)
                .success(function (collection) {
                    d.resolve(collection);
                });
            return d.promise;
        };

        self.getLesson = function (lessonId) {
            return $http.get(config.api('teacher/lesson/' + lessonId));
        };

        self.deleteLesson = function (lessonId) {
            return $http.delete(config.api('teacher/lesson/' + lessonId));
        };

        self.updateLesson = function (lessonId, lessonForm) {
            return $http.put(config.api('teacher/lesson/' + lessonId), lessonForm);
        };

        /**
         * Reports
         */

        self.getSchoolReport = function (schoolId, period) {
            return $http.post(config.api('teacher/school/{{id}}/report', {id: schoolId}), period);
        };

        self.getLessonReport = function (studentId, year, month) {
            return $http.get(config.api('teacher/student/{{id}}/lessons/{{year}}/{{month}}', {
                id: studentId,
                year: year,
                month: month
            }));
        };

        self.getReport = function (year, month) {
            return $http.get(config.api('teacher/report/{{year}}/{{month}}', {
                year: year,
                month: month
            }));
        };

        self.loadInvoice = function (schoolId, year, month, number) {
            return $http.get(config.api('teacher/invoice/{{schoolId}}/{{year}}/{{month}}/{{number}}', {
                schoolId: schoolId,
                year: year,
                month: month,
                number: number
            }))
        };

        self.getInvoiceFormData = function () {
            return $http.get(config.api('teacher/invoice/form'));
        };

        /**
         * OK
         */

        return self;
    })
;