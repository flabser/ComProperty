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
        if ($('[data-toggle=filter]')[0].checked) {
            var url = location.href.split('&')[0];
            url += '&page=' + $('.pagination a.page-active').text() + '&' + $(this).serialize();

            location.href = url;
        }
    });

    $('.pagination a').on('click', function(e) {
        if ($('[data-toggle=filter]')[0].checked) {
            e.preventDefault();

            var url = location.href.split('&')[0];
            url += '&' + $(this).serialize() + '&page=' + this.href.match('&page=(.*)')[1];

            location.href = url;
        }
    });

    $('.pagination select').on('change', function(e) {
        if ($('[data-toggle=filter]')[0].checked) {
            e.preventDefault();
        }
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
                    $('select[name=balanceholder]').select2(nb.getSelectOptions('balanceholder'));
                }
            }
        });
    }

    console.log(sbh);
});
