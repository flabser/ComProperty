$(document).ready(function() {
    // reporttemplate form
    $('form[name=reporttemplate]').on('submit', function(event) {
        event.preventDefault();

        var noty = nb.notify({
            message: nb.getText('wait_report_loaded', 'Подождите формируется отчет...')
        });

        var xhr = new XMLHttpRequest();
        xhr.open("POST", location.href + '&' + $(this).serialize(), true);
        xhr.responseType = "arraybuffer";

        xhr.onload = function(oEvent) {
            nb.uiUnblock();
            noty.remove();

            var arrayBuffer = xhr.response;
            var byteArray;
            if (arrayBuffer) {
                byteArray = new Uint8Array(arrayBuffer);

            }

            // check for a filename
            var filename = "";
            var disposition = xhr.getResponseHeader('Content-Disposition');
            if (disposition && disposition.indexOf('attachment') !== -1) {
                var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                var matches = filenameRegex.exec(disposition);
                if (matches != null && matches[1]) {
                    filename = matches[1].replace(/['"]/g, '').replace('utf-8', '');
                }
            }

            var type = xhr.getResponseHeader('Content-Type') + ';charset=utf-8';
            var blob = new Blob([arrayBuffer], { type: type });

            if (typeof window.navigator.msSaveBlob !== 'undefined') {
                // IE workaround for "HTML7007: One or more blob URLs were revoked by closing the blob for which they were created. These URLs will no longer resolve as the data backing the URL has been freed."
                window.navigator.msSaveBlob(blob, filename);
            } else {
                var URL = window.URL || window.webkitURL;
                var downloadUrl = URL.createObjectURL(blob);

                if (filename) {
                    // use HTML5 a[download] attribute to specify filename
                    var a = document.createElement("a");
                    // safari doesn't support this yet
                    if (typeof a.download === 'undefined') {
                        window.location = downloadUrl;
                    } else {
                        a.href = downloadUrl;
                        a.download = filename;
                        document.body.appendChild(a);
                        a.click();
                    }
                } else {
                    window.location = downloadUrl;
                }

                setTimeout(function() { URL.revokeObjectURL(downloadUrl); }, 100); // cleanup
            }
        };

        xhr.send(null);
        noty.show();
        nb.uiBlock();
    });
});
