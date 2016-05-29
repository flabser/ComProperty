var updateWizard = function() {
    var $wizard = $('form[name=wizard]');
    var wf = $wizard[0];
    var maxStepCount = 3;
    var autoStep = false;

    function refreshUI() {
        if (canPrevStep()) {
            $('[data-wizard-step=prev]').removeAttr('disabled');
        } else {
            $('[data-wizard-step=prev]').attr('disabled', true);
        }
        if (canNextStep()) {
            $('[data-wizard-step=next]').removeAttr('disabled');
        } else {
            $('[data-wizard-step=next]').attr('disabled', true);
        }
    }

    function canNextStep() {
        var currentStep = +wf.step.value;
        if (currentStep >= maxStepCount) {
            return false;
        }

        if (currentStep === 1) {
            return wf.filename.value && wf.uploadtype.value;
        } else if (currentStep === 2) {
            return wf.filename.value && wf.uploadtype.value && wf.status.value === '2';
        }
        return false;
    }

    function canPrevStep() {
        var currentStep = +wf.step.value;
        if (currentStep <= 1) {
            return false;
        }
        return true;
    }

    var $lastStepDesc = $('[data-wizard-step=' + maxStepCount + ']').find('.wizard_step-description');
    $wizard.find('[name=_uploadtype]').on('change', function() {
        $wizard.find('[name=uploadtype]').val(this.value);
        var uploadTypeName = $(this).parent().find('span').text();
        $lastStepDesc.removeClass('wizard_step-no-action').html(uploadTypeName);
        refreshUI();
    });
    $wizard.find('[name=_uploadtype][value=' + (wf.uploadtype.value || 'undef') + ']').attr('checked', true).trigger('change');

    $wizard.find('[data-wizard-step]').on('click', function(e) {
        e.preventDefault();

        var currentStep = wf.step.value;
        var step = $(this).data('wizard-step');
        if (step === 'next') {
            if (canNextStep()) {
                wf.step.value = +currentStep + 1;
            } else {
                return false;
            }
        } else if (step === 'prev') {
            if (canPrevStep()) {
                wf.step.value = +currentStep - 1;
            } else {
                return false;
            }
        } else {
            wf.step.value = step;
        }

        var data = {
            fsid: wf.fsid.value,
            step: wf.step.value,
            uploadtype: wf.uploadtype.value
        };

        location.href = wf.action + '&' + $.param(data);
    });

    $wizard.find('.js-check').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        var $btn = $(this);
        $btn.attr('disabled', true);
        var fileName = $("input[name=filename]").val();
        var fsid = $("input[name=fsid]").val();
        //
        checkFile(fileName, fsid, $wizard).then(function(result) {
            if (autoStep && result.objects.length && result.objects[0].status === 2) {
                insertParam('step', 3);
            }
            reloadPage();
        }, function(err) {
            reloadPage();
        });
    });

    $wizard.find('.js-select-balance-holder').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        //$(this).parents('.panel').addClass('open');
        nbApp.choiceBalanceHolder(this, function() {
            //setBalanceholderToStorage();
            //toggleLoadButtonState($tpl);
        });
        $("body").find('.errormsg').remove();
    });

    $wizard.find('.js-select-readers').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        //$(this).parents('.panel').addClass('open');
        nbApp.choiceReaders(this, function() {
            //setReaderToStorage();
            //setReaderNameToStorage();
            //toggleLoadButtonState($tpl);
        });
        $("body").find('.errormsg').remove();
    });

    $wizard.find('.js-step-3').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();

        var fileName = $('input[name=filename]').val();
        var fsid = $('input[name=fsid]').val();
        var uploadType = $('input[name=uploadtype]').val();

        if (uploadType === 'writeoff') {
            nbApp.confirmWriteOff(function() {
                loadFile(fileName, $wizard.serialize(), fsid).then(function(result) {
                    console.log('writeoff', result);

                    if (result.type === 'OK') {
                        insertParam('step', 4);
                    }
                    reloadPage();
                }, function() {
                    reloadPage();
                });
            });
        } else if (uploadType === 'transfer') {
            nbApp.confirmTransfer(function() {
                loadFile(fileName, $wizard.serialize(), fsid).then(function(result) {
                    console.log('transfer', result);

                    if (result.type === 'OK') {
                        insertParam('step', 4);
                    }
                    reloadPage();
                }, function() {
                    reloadPage();
                });
            });
        } else if (uploadType === 'upload') {
            loadFile(fileName, $wizard.serialize(), fsid).then(function(result) {
                console.log('upload', result);

                if (result.type === 'OK') {
                    insertParam('step', 4);
                }
                reloadPage();
            }, function() {
                reloadPage();
            });
        }
    });

    $wizard.find('.js-select-recipients').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        nbApp.choicePropertyRecipient(this, function() {
            //toggleLoadButtonState($tpl);
            //$('.js-load').attr('disabled', false);
            //setRecipientToStorage();
        });
        $wizard.find('.errormsg').remove();
    });

    refreshUI();
};

$(document).ready(function() {
    if ($('form[name=wizard]')) {
        updateWizard();
    }
});
