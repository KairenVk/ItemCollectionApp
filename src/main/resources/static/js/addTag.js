$(document).ready(function () {

        $("#newTagBtn").click(function () {

        $newid = $(this).data("increment");
        $tagsDiv = $("#tagsDiv").wrap('<div/>').parent().html();
        $('#tagsDiv').unwrap();
        $($tagsDiv).insertAfter($(".tagsDiv").last());
        $(".tagsDiv").last().attr('id',   "tagsDiv" + '_' + $newid);
        $("#tagsDiv" + '_' + $newid).append('<button type="button" class="btn btn-danger removeTagBtn" data-id="tagsDiv'+'_'+ $newid+'">Remove</button>');
        $newid++;
        $(this).data("increment", $newid);

        });

        $(document).on('click', '.removeTagBtn', function () {

		$divId = $(this).data("data-id");
		$("#"+$divId).remove();
		$inc = $("#repeatTagBtn").data("increment");
		$("#repeatTagBtn").data("increment", $inc-1);

	    });
});