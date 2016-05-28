$(document).ready(function() {
    var $wizard = $(".wizard");

    $("body").find('.js-check').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        var $btn = $(this);

        //$btn.attr('disabled', true);
        var fileName = $("input[name=filename]").val();
        var fsid = $("input[name=fsid]").val();
        //
        checkFile(fileName, fsid, $wizard).then(function(result) {
            console.log('check', result);

            if (result.objects.length && result.objects[0].status === 2) {
                insertParam('step', 3);
            }
            reloadPage();
        }, function(err) {
            reloadPage();
        });
    });

    $("body").find('.js-select-balance-holder').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        //$(this).parents('.panel').addClass('open');
        nbApp.choiceBalanceHolder(this, function() {
            //setBalanceholderToStorage();
            //toggleLoadButtonState($tpl);
        });
        $("body").find('.errormsg').remove();
    });

    $("body").find('.js-select-readers').on('click', function(e) {
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
});
