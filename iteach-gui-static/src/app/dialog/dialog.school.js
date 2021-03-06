angular.module('iteach.dialog.school', [])
    .controller('dialogSchool', function ($log, $scope, $modalInstance, modalController, notificationService, initialSchool) {

        $scope.colourPattern = /#[0-9A-Fa-f]{6}/;
        $scope.hourlyRatePattern = /^([A-Z]{3} )?\d+(\.\d+)?$/;
        $scope.vatRatePattern = /^([0-9]+([\.,][0-9]+)?)$/;
        $scope.school = initialSchool;

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel')
        }

        $scope.submit = function (isValid) {
            if (isValid) {
                modalController.onSubmit($scope.school).then(
                    function () {
                        $modalInstance.close('ok')
                    },
                    function (message) {
                        $scope.error = message
                    }
                )
            }
        }

        $modalInstance.opened.finally(function () {
            notificationService.pushScope($scope)
        });

        $modalInstance.result.finally(function () {
            notificationService.popScope()
        });

    })
;