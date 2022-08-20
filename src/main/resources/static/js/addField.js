$(document).ready(function () {

        $("#repeatFieldBtn").click(function () {
        $newid = $(this).data("increment");
        $fieldType= $("#fieldsDiv").find('select[name="fieldType"]').val();
        console.log($fieldType);
        $newFieldDiv = $("<div id=\"newFieldDiv\"></div>");
        $newFieldDiv.unwrap();
        $newFieldDiv.insertAfter($(".fieldsDiv").last());
        $newFieldDiv.last().attr('id',   "fieldType" + '_' + $newid);
        switch ($fieldType) {
            case 'String':
                $($newFieldDiv).append('<input type="text" id="stringField" name="stringField[]"/> <button type="button" class="btn btn-danger removeFieldBtn" data-id="fieldsDiv'+'_'+ $newid+'">Remove</button>');
                break;
                }

        $newid++;
        $(this).data("increment", $newid);

        });

        $(document).on('click', '.removeFieldBtn', function () {

		$divId = $(this).data("id");
		$("#"+$divId).remove();
		$inc = $("#repeatFieldBtn").data("increment");
		$("#repeatFieldBtn").data("increment", $inc-1);

	    });
});