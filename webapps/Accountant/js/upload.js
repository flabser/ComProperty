function uploadUpdate(fileInput, fsid) {
    var formData = new FormData();
    formData.append('file', fileInput.files[0]);
    formData.append('fsid', fsid);
    formData.append('fieldname', fileInput.name);
    var time = new Date().getTime();

    return $.ajax({
        url: 'UploadFile?time=' + time,
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        data: formData,
        dataType: 'json',
        xhr: function() {
            var customXhr = $.ajaxSettings.xhr();
            if (customXhr.upload) {
                customXhr.upload.addEventListener('progress', onProgress, false);
            }
            return customXhr;
        },
        success: function(result) {
            var fileName = result.files[0];
            if(fileInput.name == 'uporder'){
                $(".update-order").text(fileName);
            }else {
                //renderFilePanel(fileName, fsid);
                //clearLocalStorage();
                //$("#btn-update-file-excel").addClass("disabled");
            }
            return result;
        },
        error: function(err) {
            console.log(err);
        },
        complete: function() {
            $('progress').attr({
                value: 0,
                max: 100
            });
            fileInput.form.reset();
            insertParam('fsid', fsid);
            insertParam('step', 1);
            insertParam('uploadtype', $("input[name=uploadtype]:checked").val());
            if(fileInput.name != 'uporder'){
                reloadPage()
            }
        }
    });
}

function onProgress(e) {
    if (e.lengthComputable) {
        $('progress').attr({
            value: e.loaded,
            max: e.total
        });
    }
}

function loadFile(fileId, data, fsid) {
    nb.uiBlock();

    var noty = nb.notify({
        type: 'info',
        message: 'Идет загрузка данных. Пожалуйста подождите...'
    }).show();

    return $.ajax({
        type: 'post',
        dataType: 'json',
        url: 'Provider?id=update-file&fileid=' + encodeURIComponent(fileId) + '&fsid=' + fsid,
        data: data,
        success: function(result) {
            return result;
        },
        error: function(err) {
            nb.notify({
                type: 'error',
                message: 'Ошибка загрузки'
            }).show(2000);
            return err;
        },
        complete: function() {
            nb.uiUnblock();
            noty.remove();
        }
    });
}

function delFile(fileId, fsid) {
    return $.ajax({
        type: 'delete',
        dataType: 'json',
        url: 'Provider?id=update-file&fileid=' + encodeURIComponent(fileId) + '&fsid=' + fsid
    });
}

function checkFile(fileId, fsid, $context) {
    nb.uiBlock();

    var stopIfError = $('input[name=stopiferror]', $context).serialize();
    if (stopIfError != '') {
        stopIfError = '&' + stopIfError;
    }

    var uploadtype = $('input[name=uploadtype]', $context).serialize();
    if (uploadtype != '') {
        uploadtype = '&' + uploadtype;
    }

    var noty = nb.notify({
        type: 'info',
        message: 'Идет проверка структуры файла. Пожалуйста подождите...'
    }).show();

    return $.ajax({
        type: 'get',
        dataType: 'html',
        url: 'Provider?id=check-file-structure&fileid=' + encodeURIComponent(fileId) + '&fsid=' + fsid + stopIfError + uploadtype,
        success: function(data) {
            return data;
        },
        error: function() {
            return false;
        },
        complete: function() {
            nb.uiUnblock();
            noty.remove(200);
        }
    });
}

function renderFilePanel(fileName, fsid) {
    var $cacheUpdateForm = $('form[name=js-init-update-panel][data-file-name="' + fileName + '"]');
    var initMode = $cacheUpdateForm.length;
    var template;
    var $tpl;
    var activeFile = getLastFileFromStorage();
    if (initMode) {
        $tpl = $cacheUpdateForm;
    } else {
        template = $('#tpl_update_file_panel').clone();
        $tpl = $(template.html().trim());
        $tpl.attr('data-file-name', fileName);
    }

    $tpl.attr('name', 'form' + (new Date().getTime()));

    var t_link = $tpl.find('.js-link').attr('href');
    $tpl.find('.js-link').attr('href', t_link + fileName).html(fileName);

    $tpl.find('.js-check').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        var $btn = $(this);

        $btn.attr('disabled', true);
        setLastFileToStorage(fileName);
        //
        checkFile(fileName, fsid, $tpl).then(function(result) {
            /*$btn.parents('.panel').addClass('open');
             if (result == '') {
             $tpl.find('.js-load').removeAttr('disabled');
             $tpl.find('.js-select-balance-holder').removeAttr('disabled');
             $tpl.find('.js-select-readers').removeAttr('disabled');
             }
             $tpl.find('.js-check-result').html(result);*/
            //
            reloadPage();
        }, function(err) {
            // $btn.parents('.panel').addClass('open');
            // $tpl.find('.js-check-result').html(err.statusText);
            //
            reloadPage();
        });
    });

    var status = $("input[name=status]").val();
    if(status == 1){
        $('.js-check').attr("disabled", false);
    }

    $tpl.find('.js-sign').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        var $btn = $(this);

        knca.signFile().then(function(signData) {
            if (signData !== 'cancel') {
                if (signData.filePath.indexOf(fileName) != -1) {
                    $('<input type="text" name="sign" class="disabled" readonly value="' + signData.sign + '" />').appendTo($tpl.find('.panel__body'));
                    $('<span class=update-file-signed>' + nb.getText('signed', 'Подписан') + '</span>').appendTo($tpl.find('.js-link'));
                    $btn.attr('disabled', true);
                } else {
                    nb.notify({
                        type: 'error',
                        message: 'Для подписи выберите файл: ' + fileName
                    }).show('click');
                }
            }
            return signData;
        });
    });

    $tpl.find('.js-delete').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        delFile(fileName, fsid).then(function() {
            $tpl.remove();
            $("#btn-update-file-excel, .js-check").removeClass("disabled");
            clearLocalStorage()
        });
    });

    $tpl.find('.js-load').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        setLastFileToStorage(fileName);
        if($('input[name=writeoff]').is(':checked')){
            nbApp.confirmWriteOff(function() {
                loadFile(fileName, $tpl.serialize(), fsid).then(function() {
                    reloadPage();
                }, function() {
                    reloadPage();
                });
            });
        }else{
            if($('input[name=istransfer]').is(':checked')){
                nbApp.confirmTransfer(function() {
                    loadFile(fileName, $tpl.serialize(), fsid).then(function() {
                        reloadPage();
                    }, function() {
                        reloadPage();
                    });
                });
            }else{
                $(this).attr('disabled', true);
                loadFile(fileName, $tpl.serialize(), fsid).then(function () {
                    // $tpl.addClass('upload-success');
                    reloadPage();
                }, function () {
                    reloadPage();
                });
            }
        }

    });

    $tpl.find('.js-select-balance-holder').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).parents('.panel').addClass('open');
        nbApp.choiceBalanceHolder(this, function() {
            setBalanceholderToStorage();
            toggleLoadButtonState($tpl);
        });
        $tpl.find('.errormsg').remove();
    });

    $tpl.find('.js-select-readers').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).parents('.panel').addClass('open');
        nbApp.choiceReaders(this, function() {
            setReaderToStorage();
            setReaderNameToStorage();
            toggleLoadButtonState($tpl);
        });
        $tpl.find('.errormsg').remove();
    });

    $tpl.find('.js-select-recipients').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).parents('.panel').addClass('open');
        nbApp.choicePropertyRecipient(this, function() {
            toggleLoadButtonState($tpl);
            $('.js-load').attr('disabled', false);
            setRecipientToStorage();
        });
        $tpl.find('.errormsg').remove();
    });

    $tpl.find('input[name=uploadtype]').on('change', function(e) {
        if($(this).val() == "transfer"){
            $(".js-attach-order").css("display","inline-block");
        }else{
            $(".js-attach-order").css("display","none");
        }
        toggleLoadButtonState($tpl)
    });

    if (activeFile == fileName) {
        $tpl.find('.panel').addClass('open');
        $tpl.find('.panel-title').addClass('blink-anim');
    }

    if (!initMode) {
        $('.js-uploaded-files').append($tpl);
    }
}

function reloadPage() {
    location.reload();
}

function setLastFileToStorage(fileId) {
    sessionStorage.setItem('accountant_update_last_file', fileId);
}

function getLastFileFromStorage() {
    return sessionStorage.getItem('accountant_update_last_file');
}

function setReaderToStorage(){
    var readers = [];
    $("input[name=readers]").each(function(){
        readers.push($(this).val());
    });
    localStorage.setItem("reader", JSON.stringify(readers));
}

function getReaderFromStorage() {
   return JSON.parse(localStorage.getItem("reader"));
}

function getReaderNameFromStorage() {
    return localStorage.getItem('readername');
}

function setReaderNameToStorage(){
    localStorage.setItem("readername", $(".update-readers").html());
}

function setRecipientToStorage(){
    localStorage.setItem("recipient", $("input[name=recipients]").val());
    localStorage.setItem("recipientname", $(".update-recipients").html());
}

function getRecipientFromStorage() {
    return localStorage.getItem('recipient');
}

function getRecipientNameFromStorage() {
    return localStorage.getItem('recipientname');
}

function setBalanceholderToStorage(){
    localStorage.setItem("balanceholder", $("input[name=balanceholder]").val());
    localStorage.setItem("balanceholdername", $(".update-balance-holder").html());
}

function getBalanceholderFromStorage() {
    return localStorage.getItem('balanceholder');
}

function getBalanceholderNameFromStorage() {
    return localStorage.getItem('balanceholdername');
}

function loadDataLocalStorage(){
    if(getReaderNameFromStorage() != 'null'){
        $(".update-readers").html(getReaderNameFromStorage());
        var readers = getReaderFromStorage();
        $(readers).each(function(){
            $("input[name=balanceholder]").parent().append("<input type='hidden' name='readers' value='"+this+"'></input>");
        });
    }
    if(getBalanceholderNameFromStorage() != 'null'){
        $(".update-balance-holder").html(getBalanceholderNameFromStorage());
    }
}

function clearLocalStorage(){
    localStorage.setItem("balanceholder", null);
    localStorage.setItem("balanceholdername", null);
    localStorage.setItem("readername", null);
    localStorage.setItem("reader", null);
}

function toggleLoadButtonState($form) {
    var b = $form.find('[name=balanceholder]').val();
    var r = $form.find('[name=readers]').val();
    if (b && r && b != '' && r !='') {
        $form.find('.js-load').attr('disabled', false);
    }else{
        $form.find('.js-load').attr('disabled', true);
    }
}


function insertParam(_key, _value) {
    var key = encodeURI(_key);
    var value = encodeURI(_value);
    var kvp = document.location.search.substr(1).split('&');
    var i = kvp.length;
    var x;

    while (i--) {
        x = kvp[i].split('=');
        if (x[0] == key) {
            x[1] = value;
            kvp[i] = x.join('=');
            break;
        }
    }
    if (i < 0) {
        kvp[kvp.length] = [key, value].join('=');
    }
    history.replaceState(null, null, location.pathname + '?' + kvp.join('&'));
}

function initCachedUpdateForm() {
    if (location.search.indexOf('fsid') !== -1) {
        var fsid = location.search.split('fsid=')[1];
        $('form[name=js-init-update-panel][data-file-name]').each(function() {
            renderFilePanel($(this).data('file-name'), fsid);
        });
    }
}

/*$(document).ready(function() {
    initCachedUpdateForm();
    nb.fetchTranslations();
    loadDataLocalStorage();
    if($(".transferproperty").prop("checked") == true){
        $(".js-attach-order").css("display","inline-block");
    }
    toggleLoadButtonState($(".transferproperty").closest("form"));
    $("button").attr("disabled", false);
});*/

$(document).ready(function() {
    var $wizard = $(".wizard");
    $wizard.find('.js-step-1').on('click', function(e) {
        insertParam('step', 1);
        reloadPage();
    });
    $wizard.find('.js-step-2').on('click', function(e) {
        insertParam('step', 2);
        insertParam('uploadtype', $("input[name=uploadtype]:checked").val());
        reloadPage();
    });
   $("body").find('.js-check').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        var $btn = $(this);

        //$btn.attr('disabled', true);
        var fileName= $("input[name=filename]").val();
        var fsid= $("input[name=fsid]").val();
        //
        checkFile(fileName, fsid, $wizard).then(function(result) {
            /*$btn.parents('.panel').addClass('open');
             if (result == '') {
             $tpl.find('.js-load').removeAttr('disabled');
             $tpl.find('.js-select-balance-holder').removeAttr('disabled');
             $tpl.find('.js-select-readers').removeAttr('disabled');
             }
             $tpl.find('.js-check-result').html(result);*/
            //
            //reloadPage();
        }, function(err) {
            // $btn.parents('.panel').addClass('open');
            // $tpl.find('.js-check-result').html(err.statusText);
            //
            //reloadPage();
        });
    });
});
