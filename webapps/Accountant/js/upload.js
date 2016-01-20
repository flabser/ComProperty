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
        success: function(uploadResult) {
            var fileName = fileInput.files[0].name;
            var tpl = [];
            tpl.push('<li>');
            tpl.push('<a href="Provider?type=getattach&key=' + fileName + '">' + fileName + '</a>');
            tpl.push(' <button type="button" class="btn btn-sm" onclick="checkFileStructure(\'' + fileName + '\', 0)">проверить</button>');
            tpl.push('</li>');
            $('.js-uploaded-files').append(tpl.join(''));

            fileInput.form.reset();
            return uploadResult;
        },
        error: function(err) {
            fileInput.form.reset();
            console.log(err);
        }
    });
}

function checkFileStructure(fileid, trId) {
    nb.utils.blockUI();

    var noty = nb.utils.notify({
        type: 'info',
        message: 'Идет проверка структуры файла. Пожалуйста подождите...'
    }).show();

    $.ajax({
        type: "get",
        datatype: "html",
        url: "Provider?type=page&id=check_file_structure",
        data: "fileid=" + fileid,
        success: function(data) {
            $("#checker_result").html(data);

            console.log(data);

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
