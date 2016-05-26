$('#close-description').click(function() {
    closeDescription();
    return false;
});

function openDescription(id) {
    var params = {
        id: id
    };

    $.getJSON("starservice", params, function(data) {
        if (data) {
            $("#star-id").attr("value", data.id);
            $("#star-description").val(data.description);
            $("#description-box").show();
            resizeTextarea($("#star-description"));
        } else {
            console.log('Failed to get star data from star service');
        }
    });
}

function closeDescription() {
    $("#description-box").hide();
}

function resizeTextarea( textarea ) {
    var hiddenDiv = $('.hiddendiv').first();

    if (hiddenDiv.length) {
        hiddenDiv.text(textarea.val() + '\n');
        var content = hiddenDiv.html().replace(/\n/g, '<br>');
        hiddenDiv.html(content);

        hiddenDiv.css('width', textarea.width());
        textarea.css('height', hiddenDiv.height());
    }
}