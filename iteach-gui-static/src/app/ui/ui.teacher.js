angular.module('iteach.ui.teacher', [
        'iteach.config'
    ])
    .service('uiTeacher', function ($q, $http, config) {
        var self = {};

        self.getSchools = function () {
            var d = $q.defer();
            $http.get(config.api('teacher/schol'))
                .success(function (schools) {
                    d.resolve(schools);
                })
            return d.promise;
        }

        self.createSchool = function (school) {
            var d = $q.defer();
            $http.post(config.api('teacher/school'), school)
                .success(function (school) {
                    d.resolve(school);
                })
            return d.promise;
        }

        return self;
    })
;