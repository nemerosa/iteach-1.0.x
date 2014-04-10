angular.module('iteach.dialog.invoice', [
        'iteach.service.core',
        'iteach.ui.teacher'
    ])
    .controller('dialogInvoice', function ($log, $scope, $translate, $modalInstance, calendarService, modalController, invoiceForm, notificationService, uiTeacher) {

        $scope.invoice = invoiceForm;
        if (invoiceForm.period) {
            $scope.invoice.year = invoiceForm.period.year;
            $scope.invoice.month = invoiceForm.period.month;
        }

        uiTeacher.getSchools().then(function (schools) {
            $scope.schools = schools.resources;
        });

        $scope.months = calendarService.getMonths();

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel')
        };

        $scope.generate = function () {
            // Invoice form
            var invoice = {
                schoolId: $scope.invoice.schoolId,
                year: $scope.invoice.year,
                month: $scope.invoice.month,
                number: $scope.invoice.number
            };
            // Launching the generation
            $scope.generating = true;
            uiTeacher.generateInvoice(invoice).success(function () {
                // TODO Controlling the generation
            });
        };

        // TODO Not called any longer
        $scope.submit = function (isValid) {
            if (isValid) {
                var invoice = {
                    schoolId: $scope.invoice.schoolId,
                    period: {
                        year: $scope.invoice.year,
                        month: $scope.invoice.month
                    },
                    number: $scope.invoice.number
                };
                console.log('Invoice form', invoice);
                modalController.onSubmit(invoice).then(
                    function () {
                        $modalInstance.close('ok')
                    },
                    function (message) {
                        $scope.error = message
                    }
                )
            }
        };

        $modalInstance.opened.finally(function () {
            notificationService.pushScope($scope)
        });

        $modalInstance.result.finally(function () {
            notificationService.popScope()
        });

    })
;