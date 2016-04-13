// for test only
$(document).ready(function() {

    if (!$('[data-action=sign]').length) {
        return;
    }

    // plain
    $('[data-action=sign]').click(function() {
        var data = document.getElementById('text_for_sign').value;

        knca.signPlainData(data).then(function(sign) {
            document.getElementById('text_sign').value = sign;
        });
    });
    $('[data-action=verify]').click(function() {
        var data = document.getElementById('text_for_sign').value;
        var sign = document.getElementById('text_sign').value;

        knca.verifyPlainData(data, sign).then(function(verifyResult) {
            document.getElementById('verify-result').innerHTML = verifyResult;
        });
    });

    // xml
    document.getElementById('text_for_sign').addEventListener('change', function(event) {
        var xml = '<xmlsign><description>' + event.target.value + '</description></xmlsign>';
        document.getElementById('xml_for_sign').value = xml;
    }, false);

    $('[data-action=sign-xml]').click(function() {
        var xmlData = document.getElementById('xml_for_sign').value;

        knca.signXml(xmlData).then(function(sign) {
            document.getElementById('xml_sign').value = sign;
        });
    });
    $('[data-action=verify-xml]').click(function() {
        var xmlSignature = document.getElementById('xml_sign').value;

        knca.verifyXml(xmlSignature).then(function(verifyResult) {
            document.getElementById('verify-xml-result').innerHTML = verifyResult;
        })
    });

    // file
    $('[data-action=sign-files]').click(function() {
        var file = $('input[name=fileid][data-path]');
        var filePath = file.data('path');

        knca.createCMSSignatureFromFile(filePath).then(function(sign) {
            $('<p>' + sign + '</p>').appendAfter(file);
        });
    });
    $('[data-action=verify-files]').click(function() {

    });
});
