$(document).ready(function () {

    $("#newTagBtn").click(function () {

        $newid = $(this).data("increment");
        $tagsDiv = $("#itemTags");
        $newDiv = '';
        $newDiv += '<div class="tagsDiv mb-3" id="tagsDiv_' + $newid + '">';
        $newDiv += '<input type="text" class="tagInput" name="tags[]"/>';
        $newDiv += '<button type="button" class="btn btn-danger removeTagBtn" data-id="tagsDiv'+'_'+ $newid+'">Remove</button>';
        $newDiv += '</div>';
        $tagsDiv.append($newDiv);
        $.getScript('/js/tagAutocomplete.js', addAutocomplete($('.tagInput')));
        $(this).data("increment", ++$newid);

    });

    $(document).on('click', '.removeTagBtn', function () {

        $divId = $(this).data("id");
        $("#"+$divId).remove();
        $inc = $("#repeatTagBtn").data("increment");
        $("#repeatTagBtn").data("increment", $inc-1);

    });
});