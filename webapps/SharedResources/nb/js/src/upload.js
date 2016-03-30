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
            $('.btn-remove-file', $attNode).click(function() {
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
        var uploadBtn = this;
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
                $(uploadBtn).attr('disabled', true);
                nb.upload($fileInput[0]).always(function() {
                    $(uploadBtn).attr('disabled', false);
                });

                if (!location.search.match('&fsid=')) {
                    history.replaceState(null, null, location.href + '&fsid=' + fsId);
                }
            });

            $(this).click(function() {
                $fileInput.click();
            });
        }

        $('.attachments-file', '[data-upload-files=' + uploadName + ']').each(function() {
            var $self = this;
            var url = $('a[data-file]', $self).attr('href');
            $('.btn-remove-file', $self).on('click', function() {
                return $.ajax({
                    type: 'DELETE',
                    dataType: 'json',
                    url: url,
                    success: function() {
                        $self.remove();
                    }
                });
            });
        });
    });
});
