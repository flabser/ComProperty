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
            var $attNode = $(nb.template('attachments', result));
            $('[data-upload-files=' + inputName + ']').prepend($attNode);

            // init
            $('.btn-remove', $attNode).click(function() {
                $attNode.remove();
            });
            //

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
        if (this.form.fsid && this.form.fsid.value) {
            fsId = this.form.fsid.value;
        }

        if ($('[type=file][name=' + uploadName + ']').length === 0) {
            if (this.form.fsid) {
                this.form.fsid.value = fsId;
            } else {
                $('<input type=hidden name=fsid value="' + fsId + '"/>').appendTo(this.form);
            }

            var $fileForm = $('<form class=hidden><input type=file name="' + uploadName + '" /><input type=hidden name=fsid value="' + fsId + '"/></form>').appendTo('body');
            var $fileInput = $fileForm.find('input[type=file]');

            $fileInput.on('change', function() {
                nb.upload($fileInput[0]);

                if (!location.search.match('&fsid=')) {
                    history.replaceState(null, null, location.href + '&fsid=' + fsId);
                }
            });

            $(this).click(function() {
                $fileInput.click();
            });
        }
    });
});
