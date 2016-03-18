nb.upload = function(fileInput) {
    var inputName = fileInput.name;
    var formData = new FormData(fileInput.form);

    return $.ajax({
        url: nb.api.upload + '?time=' + new Date().getTime(),
        method: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        data: formData,
        xhr: function() {
            var _xhr = $.ajaxSettings.xhr();
            if (_xhr.upload) {
                _xhr.upload.addEventListener('progress', function(e) { nb.uploadProgress(e, inputName); }, false);
            }
            return _xhr;
        },
        success: function(result, xhr) {
            $('[data-upload-files=' + inputName + ']').append(nb.template('attachments', result.files));
            return result;
        },
        error: function(err) {
            $('[data-upload-files=' + inputName + ']').append("error");
            return err;
        },
        complete: function() {
            fileInput.form.reset();
            $('#progress_' + inputName).val(0);
        }
    });
};

nb.uploadProgress = function(e, inputName) {
    if (e.lengthComputable) {
        $('#progress_' + inputName).attr({
            value: e.loaded,
            max: e.total
        });
    }
};

$(document).ready(function() {
    var fsId = new Date().getTime();

    $('[data-upload]').each(function() {
        var uploadName = $(this).data('upload');

        if ($('[type=file][name=' + uploadName + ']').length === 0) {
            var $fileForm = $('<form class=hidden><input type=file name="' + uploadName + '" /></form>').appendTo('body');
            var $fileInput = $fileForm.find('input[type=file]');

            $fileInput.on('change', function() {
                nb.upload($fileInput[0]);
            });

            $(this).click(function() {
                $fileInput.click();
            });
        }
    });
});
