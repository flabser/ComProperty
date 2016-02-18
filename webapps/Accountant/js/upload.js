function uploadUpdate(fileInput, fsid) {
    var formData = new FormData();
    formData.append('file', fileInput.files[0]);
    formData.append('fsid', fsid);
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

function loadFile(fileId, data) {
    nb.uiBlock();

    var noty = nb.notify({
        type: 'info',
        message: 'Идет загрузка данных. Пожалуйста подождите...'
    }).show();

    return $.ajax({
        type: 'post',
        datatype: 'html',
        url: 'Provider?type=page&id=load-file-data&fileid=' + fileId,
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

function delFile(fileId) {
    return $.ajax({
        type: 'post',
        datatype: 'html',
        url: 'Provider?type=page&id=delete-attach&fileid=' + fileId
    });
}

function checkFile(fileId) {
    nb.uiBlock();

    var noty = nb.notify({
        type: 'info',
        message: 'Идет проверка структуры файла. Пожалуйста подождите...'
    }).show();

    return $.ajax({
        type: 'get',
        datatype: 'html',
        url: 'Provider?type=page&id=check-file-structure&fileid=' + fileId,
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
    })
}

function renderFilePanel(fileName) {
    var template = $('#tpl_update_file_panel').clone();
    var $tpl = $(template.html().trim());

    $tpl.attr('name', 'form' + (new Date().getTime()));

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
                $tpl.find('.js-select-readers').removeAttr('disabled');
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
        loadFile(fileName, $tpl.serialize()).then(function() {
            $tpl.addClass('upload-success');
        });
    });

    $tpl.find('.js-select-balance-holder').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).parents('.panel').addClass('open');
        nbApp.dialogChoiceBalanceHolder(this);
    });

    $tpl.find('.js-select-readers').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).parents('.panel').addClass('open');
        nbApp.dialogChoiceReaders(this);
    });

    $('.js-uploaded-files').append($tpl);
}
