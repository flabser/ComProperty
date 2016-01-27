function uploadUpdate(fileInput) {
    var formData = new FormData();
    formData.append('file', fileInput.files[0]);
    var time = new Date().getTime();

    return $.ajax({
        url: 'UploadFile?time=' + time,
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        data: formData,
        datatype: 'json',
        xhr: function() {
            var customXhr = $.ajaxSettings.xhr();
            if (customXhr.upload) {
                customXhr.upload.addEventListener('progress', onProgress, false);
            }
            return customXhr;
        },
        success: function(result) {
            var fileName = result.files[0];
            renderFilePanel(fileName);
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

function loadFile(fileId) {
    nb.utils.blockUI();

    var noty = nb.utils.notify({
        type: 'info',
        message: 'Идет загрузка данных. Пожалуйста подождите...'
    }).show();

    return $.ajax({
        type: 'post',
        datatype: 'html',
        url: 'Provider?type=page&id=load-file-data&fileid=' + fileId,
        success: function(result) {
            return result;
        },
        error: function(err) {
            nb.utils.notify({
                type: 'error',
                message: 'Ошибка загрузки'
            }).show(2000);
            return err;
        },
        complete: function() {
            nb.utils.unblockUI();
            noty.remove();
        }
    });
}

function delFile(fileId) {
    return $.ajax({
        type: 'post',
        datatype: 'html',
        url: 'Provider?type=page&id=delete-attach&fileid=' + fileId
    });
}

function checkFile(fileId) {
    nb.utils.blockUI();

    var noty = nb.utils.notify({
        type: 'info',
        message: 'Идет проверка структуры файла. Пожалуйста подождите...'
    }).show();

    return $.ajax({
        type: 'get',
        datatype: 'html',
        url: 'Provider?type=page&id=check-file-structure',
        data: {
            fileid: fileId
        },
        success: function(data) {
            return data;
        },
        error: function() {
            return false;
        },
        complete: function() {
            nb.utils.unblockUI();
            noty.remove(200);
        }
    })
}

function renderFilePanel(fileName) {
    var template = $('#tpl_upload_file').clone();
    var $tpl = $(template.html().trim());

    var t_link = $tpl.find('.js-link').attr('href');
    $tpl.find('.js-link').attr('href', t_link + fileName).html(fileName);

    $tpl.find('.js-check').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        var $btn = $(this);
        $btn.attr('disabled', true);
        //
        checkFile(fileName).then(function(result) {
            $btn.parents('.panel').addClass('open');
            if (result == '') {
                $tpl.find('.js-load').removeAttr('disabled');
                $tpl.find('.js-select-balance-holder').removeAttr('disabled');
            }
            $tpl.find('.js-check-result').html(result);
        }, function(err) {
            $btn.parents('.panel').addClass('open');
            $tpl.find('.js-check-result').html(err.statusText);
        });
    });

    $tpl.find('.js-delete').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        delFile(fileName).then(function() {
            $tpl.remove();
        });
    });

    $tpl.find('.js-load').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        loadFile(fileName);
    });

    $tpl.find('.js-select-balance-holder').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        nbApp.dialogBalanceHolder(this);
    });

    $('.js-uploaded-files').append($tpl);
}

$(function() {
    $('[data-action=save_and_close]').click(function() {
        nb.xhr.saveDocument().then(function(result) {
            console.log(result);
        });
    });
});
