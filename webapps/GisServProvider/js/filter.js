$(document).ready(function() {
    $('[data-toggle=filter]').on('change', function(event) {
        var targetSelector = $(this).data('target');
        var $panel = $(targetSelector);

        if (this.checked) {
            $panel.addClass('open');
        } else {
            $panel.removeClass('open');
        }
    });

    $('select', '#property-filter').on('change', function(e) {
        var urlParams = location.search.split('&');
        for (var i in urlParams) {
            if (urlParams[i].split('=')[0] == this.name || urlParams[i].split('=')[0] == 'page') {
                urlParams.splice(i, 1);
            }
        }

        var url = urlParams.join('&') + '&' + $(this).serialize();
        location.href = location.pathname + url;
    });

    $('.pagination select').on('change', function(e) {
        e.preventDefault();
    });

    var sbh = [];
    var ubh = location.search.split('&');
    for (var p in ubh) {
        if (ubh[p].split('=')[0] === 'balanceholder') {
            sbh.push('ids=' + ubh[p].split('=')[1]);
        }
    }
    if (sbh.length) {
        $('[data-toggle=filter]').attr('checked', true);
        $('[data-toggle=filter]').trigger('change');

        $.ajax({
            url: 'p?id=get-organizations&' + sbh.join('&'),
            dataType: 'json',
            success: function(data) {
                var list = data.objects[0].list;
                if (list) {
                    for (var m in list) {
                        $('select[name=balanceholder]').append($('<option value="' + list[m].id + '" selected>' + list[m].name + '</option>'));
                    }
                    var opt = nb.getSelectOptions(nbApp.selectOptions['balanceholder']);
                    $('select[name=balanceholder]').select2(opt);
                }
            }
        });
    }
});
