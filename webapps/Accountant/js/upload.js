function upload(fileInput) {
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
        beforeSend: function() {
            $('#progress-bar').show();
        },
        success: function(result) {
            var fileName = result.files[0];
            var tpl = [];
            tpl.push('<li data-file="' + fileName + '">');
            tpl.push(' <a href="Provider?type=getattach&key=' + fileName + '">' + fileName + '</a>');
            tpl.push(' <button type="button" class="btn btn-sm" onclick="checkFile(\'' + fileName + '\', 0)">проверить</button>');
            tpl.push(' <button type="button" class="btn btn-sm" onclick="loadFile(\'' + fileName + '\')">загрузить</button>');
            tpl.push(' <button type="button" class="btn btn-sm" onclick="delFile(\'' + fileName + '\')">удалить</button>');
            tpl.push('</li>');
            $('.js-uploaded-files').append(tpl.join(''));

            fileInput.form.reset();
            return result;
        },
        error: function(err) {
            fileInput.form.reset();
            console.log(err);
        },
        complete: function() {
            $('progress').attr({
                value: 0,
                max: 100
            });
            $('#progress-bar').hide();
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
    $.ajax({
        type: 'get',
        datatype: 'html',
        url: 'Provider?type=page&id=load-file-data',
        data: 'fileid=' + fileId,
        success: function(result) {
            // console.log(result);
        },
        error: function(err) {
            console.log(err);
        }
    });
}

function delFile(fileId) {
    $.ajax({
        type: 'get',
        datatype: 'html',
        url: 'Provider?type=page&id=delete-attach',
        data: 'fileid=' + fileId,
        success: function(result) {
            // console.log(result);
            $('[data-file="' + fileId + '"]').remove();
        },
        error: function(err) {
            console.log(err);
        }
    });
}

function checkFile(fileid, trId) {
    nb.utils.blockUI();

    var noty = nb.utils.notify({
        type: 'info',
        message: 'Идет проверка структуры файла. Пожалуйста подождите...'
    }).show();

    $.ajax({
        type: 'get',
        datatype: 'html',
        url: 'Provider?type=page&id=check-file-structure',
        data: 'fileid=' + fileid,
        success: function(data) {
            $('#checker_result').html(data);

            // console.log(data);

            /* var result = $(xml).find("result")
             if(result.attr("status") != "success") {
                 $("#btnsavedoc").attr("disabled","disabled").addClass("ui-state-disabled")
                 $("#"+trId).remove()
                 var mess = ""
                 if(result.attr("status") == "wrong_kuf" || result.attr("status") == "empty_kuf")
                     mess =  "Неправильно заполнен КУФ номер. Пожалуйста, сверьте Ваш файл с шаблоном..."
                 else if(result.attr("status") == "parsing_file_error")
                     mess =  "Неправильный формат файла. Пожалуйста, сверьте Ваш файл с шаблоном..."
                 infoDialog(mess)
             } else{
                 $("#btnsavedoc").removeAttr("disabled").removeClass("ui-state-disabled")
                 text = "Инвентарный номер: " + result.attr("invnumber") + "<br/>"
                 text += "Наименование: " + result.attr("name") + "<br/>"
                 text += "Дата принятия на баланс: " + result.attr("acceptancedate") + "<br/>"
                 text += "Район: " + result.attr("region") + "<br/>"
                 text += "Адрес: " + result.attr("address") + "<br/>"
                 text += "Первоначальная стоимость (тг.): " + result.attr("originalcost") + "<br/>"
                 text += "Балансовая стоимость (тг.): " + result.attr("balancecost") + "<br/>"
                 text += "kuf: " + result.attr("kuf") + "<br/>"
                 infoDialogBig(text, "Проверьте правильность данных", trId)
             }*/
            noty.remove(200);

            nb.utils.unblockUI();
        },
        error: function(xhr, ajaxOptions, thrownError) {
            nb.utils.unblockUI();
            noty.remove(200);
        }
    })
}

$(function() {
    $('[data-action=save_and_close]').click(function() {
        nb.xhr.saveDocument().then(function(result) {
            // $('[data-action=close]')[0].click();
            console.log(result);
            /*nb.utils.notify({
                type: 'info',
                message: 'Сохранен'
            }).show(1000);*/
        });
    });
});
