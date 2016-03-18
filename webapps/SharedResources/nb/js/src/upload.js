nb.upload = function(fileInput, fsId) {
    var formData = new FormData();
    formData.append('file', fileInput.files);
    formData.append('fsid', fsId);

    return $.ajax({
        url: nb.api.upload,
        method: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        data: formData,
        success: function(result, xhr) {
            $('[data-upload-files=' + fileInput.name + ']').append(nb.template('attachments', result.files));
            return result;
        },
        error: function(err) {
            $('[data-upload-files=' + fileInput.name + ']').append("error");
            return err;
        },
        complete: function() {
            fileInput.form.reset();
        }
    });
};

$(document).ready(function() {
    var fsId = new Date().getTime();
    $('[data-upload]').each(function() {
        var uploadName = $(this).data('upload');
        if ($('[type=file][name=' + uploadName + ']').length === 0) {
            var $fileForm = $('<form class=hidden><input type=file name="' + uploadName + '"/></form>');
            $fileForm.appendTo('body');
            var $fileInput = $fileForm.find('input');

            $fileInput.on('change', function() {
                nb.upload($fileInput[0], fsId);
            });

            $(this).click(function() {
                $fileInput.click();
            });
        }
    });
});
